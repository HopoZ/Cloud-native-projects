package org.software.productclient.client;

import lombok.extern.slf4j.Slf4j;
import org.software.productclient.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ProductServiceFallback implements ProductClient.ProductServiceClient {
    @Override
    public Product findByProductId(Long productId) {
        log.info("findByProductId callback");
        return null;
    }

    @Override
    public List<Product> queryAllProduct() {
        log.info("queryAllProduct callback");
        return null;
    }

    @Override
    public List<Product> queryAl() {
        return null;
    }
}