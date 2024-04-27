package org.software.productclient.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.software.productclient.client.ProductClient;
import org.software.productclient.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.software.productclient.common.Result;

import javax.annotation.Resource;

@AllArgsConstructor // 使用 Lombok 自动生成带有所有参数的构造函数
@RestController // 标识该类是一个 REST 控制器
@Slf4j // 使用 Lombok 自动生成日志记录器
@RequestMapping("/api/permission")
public class PermissionClientController {
    @Resource
    private final ProductClient.PermissionServiceClient permissionServiceClient;

    @PostMapping
    public Result<?> save(@RequestBody Permission ordersSearch){
        Result<?> result = permissionServiceClient.save(ordersSearch);
        return result;
    }

    @PutMapping
    public Result<?> update(@RequestBody Permission ordersSearch){
        Result<?> result = permissionServiceClient.update(ordersSearch);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        Result<?> result = permissionServiceClient.delete(id);
        return result;
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id){
        Result<?> result = permissionServiceClient.findById(id);
        return result;
    }

    @GetMapping
    public Result<?> findAll(){
        Result<?> result = permissionServiceClient.findAll();
        return result;
    }
    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                              @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        Result<?> result = permissionServiceClient.findPage(name,pageNum,pageSize);
        return result;
    }


}
