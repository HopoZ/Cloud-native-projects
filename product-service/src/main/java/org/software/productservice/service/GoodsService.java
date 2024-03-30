package org.software.productservice.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.software.productservice.entity.Goods;
import org.software.productservice.mapper.GoodsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GoodsService extends ServiceImpl<GoodsMapper, Goods> {

    @Resource
    private GoodsMapper goodsMapper;

}
