package org.software.productservice.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.software.productservice.entity.SupplierInfo;
import org.software.productservice.mapper.SupplierInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SupplierInfoService extends ServiceImpl<SupplierInfoMapper, SupplierInfo> {

    @Resource
    private SupplierInfoMapper supplierInfoMapper;

}
