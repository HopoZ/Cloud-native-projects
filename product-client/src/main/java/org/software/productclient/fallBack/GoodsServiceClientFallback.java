package org.software.productclient.fallBack;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.software.productclient.client.ProductClient;
import org.software.productclient.entity.Goods;
import org.springframework.stereotype.Component;
import org.software.productclient.common.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class GoodsServiceClientFallback implements ProductClient.GoodsServiceClient{


    @Override
    public Result<Boolean> save(Goods goods) {
        log.info("save callback");
        return null;
    }

    @Override
    public Result<Boolean> update(Goods goods) {
        log.info("update callback");
        return null;
    }

    @Override
    public Result delete(Long id) {
        log.info("delete callback");
        return null;
    }

    @Override
    public Result<Goods> findById(Long id) {
        log.info("findById callback");
        return null;
    }

    @Override
    public Result<List<Goods>> findAll() {
        log.info("findAll callback");
        return null;
    }

    @Override
    public Result<IPage<Goods>> findPage(String name, Integer pageNum, Integer pageSize) {
        log.info("findPage callback");
        return null;
    }

    @Override
    public void export(HttpServletResponse response) throws IOException {
        log.info("export callback");
    }

    @Override
    public Result upload(String fileId) {
        log.info("upload callback");
        return null;
    }
}
