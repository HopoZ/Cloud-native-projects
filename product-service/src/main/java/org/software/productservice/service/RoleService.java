package org.software.productservice.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.software.productservice.entity.Role;
import org.software.productservice.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {

    @Resource
    private RoleMapper roleMapper;

}
