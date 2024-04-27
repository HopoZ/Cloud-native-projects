package org.software.productclient.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.software.productclient.client.ProductClient;
import org.software.productclient.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.software.productclient.common.Result;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor // 使用 Lombok 自动生成带有所有参数的构造函数
@RestController // 标识该类是一个 REST 控制器
@Slf4j // 使用 Lombok 自动生成日志记录器
@RequestMapping("/api/orders")
public class OrdersClientController {
    @Resource
    private final ProductClient.OrdersServiceClient ordersServiceClient;

    @PostMapping
    public Result<?> save(@RequestBody Orders orders){
        Result<?> result = ordersServiceClient.save(orders);
        return result;
    }

    @PutMapping
    public Result<?> update(@RequestBody Orders orders){
        Result<?> result = ordersServiceClient.update(orders);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        Result<?> result = ordersServiceClient.delete(id);
        return result;
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id){
        Result<?> result = ordersServiceClient.findById(id);
        return result;
    }

    @GetMapping
    public Result<?> findAll(){
        Result<?> result = ordersServiceClient.findAll();
        return result;
    }
    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                              @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        Result<?> result = ordersServiceClient.findPage(name,pageNum,pageSize);
        return result;
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException{
        ordersServiceClient.export(response);
    }

    @GetMapping("/upload/{fileId}")
    public Result<?> upload(@PathVariable String fileId){
        Result<?> result = ordersServiceClient.upload(fileId);
        return result;
    }
}
