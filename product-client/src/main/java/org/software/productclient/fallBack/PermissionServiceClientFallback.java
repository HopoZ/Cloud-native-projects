package org.software.productclient.fallBack;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.software.productclient.client.ProductClient;
import org.software.productclient.entity.Permission;
import org.software.productclient.entity.Role;
import org.springframework.stereotype.Component;
import org.software.productclient.common.Result;

import java.util.List;

@Slf4j
@Component
public class PermissionServiceClientFallback implements ProductClient.PermissionServiceClient{

    @Override
    public Result<?> save(Permission permission) {
        log.info("save callback");
        return null;
    }

    @Override
    public Result<?> update(Permission permission) {
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
    public Result<?> getByRoles(List<Role> roles) {
        return null;
    }

}
