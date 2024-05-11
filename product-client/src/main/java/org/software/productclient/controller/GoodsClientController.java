package org.software.productclient.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.software.productclient.client.ProductClient;
import org.software.productclient.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.*;
import org.software.productclient.common.Result;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor // 使用 Lombok 自动生成带有所有参数的构造函数
@RestController // 标识该类是一个 REST 控制器
@Slf4j // 使用 Lombok 自动生成日志记录器
@RequestMapping("/api/goods")
public class GoodsClientController {


    @Resource
    private final ProductClient.GoodsServiceClient goodsServiceClient; // ProductClient 接口的实例
    
    @PostMapping
    public Result<?> save(@RequestBody Goods goods){
        Result<?> result = goodsServiceClient.save(goods);
        return result;
    }

    @PutMapping
    public Result<?> update(@RequestBody Goods goods){
        Result<?> result = goodsServiceClient.update(goods);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        Result<?> result = goodsServiceClient.delete(id);
        return result;
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id){
        Result<?> result = goodsServiceClient.findById(id);
        return result;
    }

    @GetMapping
    public Result<?> findAll(){
        Result<?> result = goodsServiceClient.findAll();
        return result;
    }
    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                         @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                         @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        Result<?> result = goodsServiceClient.findPage(name,pageNum,pageSize);
        return result;
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException{
        goodsServiceClient.export(response);
    }

    @GetMapping("/upload/{fileId}")
    public Result<?> upload(@PathVariable String fileId){
        Result<?> result = goodsServiceClient.upload(fileId);
        return result;
    }


}
