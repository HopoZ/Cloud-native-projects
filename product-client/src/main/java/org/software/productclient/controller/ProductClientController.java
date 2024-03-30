package org.software.productclient.controller;

import lombok.AllArgsConstructor; // Lombok 自动生成所有参数构造函数
import lombok.extern.slf4j.Slf4j; // Lombok 自动生成日志记录器
import org.software.productclient.client.ProductClient; // 导入 ProductClient 接口
import org.software.productclient.model.Product; // 导入 Product 模型类
import org.springframework.beans.factory.annotation.Autowired; // Spring 自动注入依赖
import org.springframework.web.bind.annotation.GetMapping; // 定义处理 GET 请求的注解
import org.springframework.web.bind.annotation.PathVariable; // 用于绑定 URL 中的变量到方法参数的注解
import org.springframework.web.bind.annotation.RestController; // 标识类为 Spring REST 控制器

import java.util.List; // 导入 List 类型

@AllArgsConstructor // 使用 Lombok 自动生成带有所有参数的构造函数
@RestController // 标识该类是一个 REST 控制器
@Slf4j // 使用 Lombok 自动生成日志记录器

public class ProductClientController {
    @Autowired // 自动注入 ProductClient.ProductServiceClient 实例
    private final ProductClient.ProductServiceClient prodServiceClient; // ProductClient 接口的实例

    /**
     * 根据商品 id 查询商品
     * @param productId 商品 id
     * @return 返回对应商品的信息
     */
    @GetMapping("/findByProductId/{productId}") // 处理 GET 请求，根据商品 id 查询商品
    public Product findByProductId(@PathVariable Long productId) { // 使用 @PathVariable 注解绑定 URL 中的 productId 到方法参数
        // 调用 ProductClient 接口中的 findByProductId 方法，向服务端发送请求，并获取响应结果
        Product product = prodServiceClient.findByProductId(productId);
        // 打印日志信息，记录方法调用
        log.info("-------------In client findByProductId---------------");
        // 返回查询到的商品信息
        return product;
    }

    /**
     * 查询所有商品
     * @return 返回所有商品的列表
     */
    @GetMapping("/queryAllProduct") // 处理 GET 请求，查询所有商品
    public List<Product> findByProductId() { // 定义方法，返回所有商品的列表
        // 调用 ProductClient 接口中的 queryAllProduct 方法，向服务端发送请求，并获取响应结果
        List<Product> productList = prodServiceClient.queryAllProduct();
        // 打印日志信息，记录方法调用
        log.info("--------------In client queryAllProduct--------------");
        // 返回查询到的所有商品列表
        return productList;
    }
}
