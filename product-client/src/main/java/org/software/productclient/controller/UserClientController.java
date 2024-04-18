package org.software.productclient.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.software.productclient.client.ProductClient;
import org.software.productclient.entity.OrdersSearch;
import org.software.productclient.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xnio.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor // 使用 Lombok 自动生成带有所有参数的构造函数
@RestController // 标识该类是一个 REST 控制器
@Slf4j // 使用 Lombok 自动生成日志记录器
@RequestMapping("/api/user")
public class UserClientController {
    @Autowired
    private final ProductClient.UserServiceClient userServiceClient;

    @PostMapping
    public Result<?> save(@RequestBody User ordersSearch){
        Result<?> result = userServiceClient.save(ordersSearch);
        return result;
    }

    @PutMapping
    public Result<?> update(@RequestBody User ordersSearch){
        Result<?> result = userServiceClient.update(ordersSearch);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        Result<?> result = userServiceClient.delete(id);
        return result;
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id){
        Result<?> result = userServiceClient.findById(id);
        return result;
    }

    @GetMapping
    public Result<?> findAll(){
        Result<?> result = userServiceClient.findAll();
        return result;
    }
    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                              @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        Result<?> result = userServiceClient.findPage(name,pageNum,pageSize);
        return result;
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException{
        userServiceClient.export(response);
    }

    @GetMapping("/upload/{fileId}")
    public Result<?> upload(@PathVariable String fileId){
        Result<?> result = userServiceClient.upload(fileId);
        return result;
    }
}
