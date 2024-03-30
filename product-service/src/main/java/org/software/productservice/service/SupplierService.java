package org.software.productservice.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.software.productservice.entity.Supplier;
import org.software.productservice.mapper.SupplierMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SupplierService extends ServiceImpl<SupplierMapper, Supplier> {

    @Resource
    private SupplierMapper supplierMapper;

}
