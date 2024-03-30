package org.software.productservice.controller;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.software.productservice.mapper.ProductMapper;
import org.software.productservice.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
/**
 * 商品的服务控制层
 */
@AllArgsConstructor
@RestController
@Slf4j
public class ProductController {
    private final ProductMapper productMapper;
    /**
     * 根据商品 id 查询商品
     */
    @GetMapping("/findByProductId/{productId}")
    public Product findByProductId(@PathVariable Long productId) {
        Product product = productMapper.findByProductId(productId);
        log.info("-------------OK /findByProductId/{productId}--------------------");
        return product;
    }
    /**
     * 查询所有商品
     */
    @GetMapping("/queryAllProduct")
    public List<Product> findByProductId() {
        List<Product> productList = productMapper.queryAllProduct();
        log.info("-------------OK queryAllProduct--------------------");
        return productList;
    }
}