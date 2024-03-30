package org.software.productservice.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.software.productservice.entity.OrdersSearch;
import org.software.productservice.mapper.OrdersSearchMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrdersSearchService extends ServiceImpl<OrdersSearchMapper, OrdersSearch> {

    @Resource
    private OrdersSearchMapper ordersSearchMapper;

}
