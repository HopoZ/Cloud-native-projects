package org.software.productclient.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.software.productclient.client.ProductClient;
import org.software.productclient.entity.OrdersSearch;
import org.software.productclient.entity.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xnio.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor // 使用 Lombok 自动生成带有所有参数的构造函数
@RestController // 标识该类是一个 REST 控制器
@Slf4j // 使用 Lombok 自动生成日志记录器
@RequestMapping("/api/table")
public class TableClientController {
    @Autowired
    private final ProductClient.TableServiceClient tableServiceClient;

    @PostMapping
    public Result<?> save(@RequestBody Table ordersSearch){
        Result<?> result = tableServiceClient.save(ordersSearch);
        return result;
    }

    @PutMapping
    public Result<?> update(@RequestBody Table ordersSearch){
        Result<?> result = tableServiceClient.update(ordersSearch);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        Result<?> result = tableServiceClient.delete(id);
        return result;
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id){
        Result<?> result = tableServiceClient.findById(id);
        return result;
    }

    @GetMapping
    public Result<?> findAll(){
        Result<?> result = tableServiceClient.findAll();
        return result;
    }
    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                              @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        Result<?> result = tableServiceClient.findPage(name,pageNum,pageSize);
        return result;
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException{
        tableServiceClient.export(response);
    }

    @GetMapping("/upload/{fileId}")
    public Result<?> upload(@PathVariable String fileId){
        Result<?> result = tableServiceClient.upload(fileId);
        return result;
    }
}
