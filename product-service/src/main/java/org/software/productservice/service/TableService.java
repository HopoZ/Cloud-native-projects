package org.software.productservice.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.software.productservice.entity.Table;
import org.software.productservice.mapper.TableMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TableService extends ServiceImpl<TableMapper, Table> {

    @Resource
    private TableMapper tableMapper;

}
