package org.software.productservice.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.software.productservice.entity.Orders;
import org.software.productservice.mapper.OrdersMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrdersService extends ServiceImpl<OrdersMapper, Orders> {

    @Resource
    private OrdersMapper ordersMapper;

}
