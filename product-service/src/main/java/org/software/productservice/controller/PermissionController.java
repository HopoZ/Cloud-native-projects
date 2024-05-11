package org.software.productservice.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.software.productservice.common.Result;
import org.software.productservice.entity.Permission;
import org.software.productservice.entity.Role;
import org.software.productservice.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    @PostMapping
    public Result<?> save(@RequestBody Permission permission) {
        return Result.success(permissionService.save(permission));
    }

    @PutMapping
    public Result<?> update(@RequestBody Permission permission) {
        return Result.success(permissionService.updateById(permission));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        Permission permission = permissionService.getById(id);
        permissionService.delete(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(permissionService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(permissionService.list());
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                              @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                              @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return Result.success(permissionService.page(new Page<>(pageNum, pageSize), Wrappers.<Permission>lambdaQuery().like(Permission::getName, name)));
    }

    @PostMapping("/getByRoles")
    public Result<?> getByRoles(@RequestBody List<Role> roles) {
        return Result.success(permissionService.getByRoles(roles));
    }

}
