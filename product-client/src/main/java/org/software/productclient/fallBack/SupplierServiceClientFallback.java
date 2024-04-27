package org.software.productclient.fallBack;

import lombok.extern.slf4j.Slf4j;
import org.software.productclient.client.ProductClient;
import org.software.productclient.entity.OrdersSearch;
import org.software.productclient.entity.Supplier;
import org.springframework.stereotype.Component;
import org.software.productclient.common.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component
public class SupplierServiceClientFallback implements ProductClient.SupplierServiceClient{

    @Override
    public Result<?> save(Supplier supplier) {
        log.info("save callback");
        return null;
    }

    @Override
    public Result<?> update(Supplier supplier) {
        log.info("update callback");
        return null;
    }



    @Override
    public Result<?> delete(Long id) {
        log.info("delete callback");
        return null;
    }

    @Override
    public Result<?> findById(Long id) {
        log.info("findById callback");
        return null;
    }

    @Override
    public Result<?> findAll() {
        log.info("findAll callback");
        return null;
    }

    @Override
    public Result<?> findPage(String name, Integer pageNum, Integer pageSize) {
        log.info("findPage callback");
        return null;
    }

    @Override
    public void export(HttpServletResponse response) throws IOException {
        log.info("export callback");
    }

    @Override
    public Result<?> upload(String fileId) {
        log.info("upload callback");
        return null;
    }
}
