package org.software.productservice.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.software.productservice.entity.Permission;
import org.software.productservice.entity.Role;
import org.software.productservice.mapper.PermissionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionService extends ServiceImpl<PermissionMapper, Permission> {

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private RoleService roleService;

    public List<Permission> getByRoles(List<Role> roles) {
        List<Permission> permissions = new ArrayList<>();
        for (Role role : roles) {
            Role r = roleService.getById(role.getId());
            if (CollUtil.isNotEmpty(r.getPermission())) {
                for (Object permissionId : r.getPermission()) {
                    Permission permission = getById((int) permissionId);
                    if (permissions.stream().noneMatch(p -> p.getPath().equals(permission.getPath()))) {
                        permissions.add(permission);
                    }
                }
            }
        }
        return permissions;
    }

    @Transactional
    public void delete(Long id) {
        removeById(id);
        // 删除角色分配的菜单
        List<Role> list = roleService.list();
        for (Role role : list) {
            // 重新分配权限
            List<Long> newP = new ArrayList<>();
            for (Object p : role.getPermission()) {
                Long pl = Long.valueOf(p + "");
                if (!id.equals(pl)) {
                    newP.add(Long.valueOf(p + ""));
                }
            }
            role.setPermission(newP);
            roleService.updateById(role);
        }
    }
}
