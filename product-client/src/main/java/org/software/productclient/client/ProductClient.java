package org.software.productclient.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.software.productclient.entity.*;
import org.software.productclient.fallBack.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.software.productclient.common.Result;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductClient {
    /**
     * 商品服务远程调用客户端
     */
    @FeignClient(name = "product-service",path ="/api/goods",fallback = GoodsServiceClientFallback.class)
    public interface GoodsServiceClient {
        @PostMapping
        Result<Boolean> save(@RequestBody Goods goods);

        @PutMapping
         Result<Boolean> update(@RequestBody Goods goods);

        @DeleteMapping("/{id}")
         Result delete(@PathVariable("id") Long id);

        @GetMapping("/{id}")
         Result<Goods> findById(@PathVariable("id") Long id);

        @GetMapping
         Result<List<Goods>> findAll();
        @GetMapping("/page")
         Result<IPage<Goods>> findPage(@RequestParam(name = "name",required = false, defaultValue = "") String name,
                @RequestParam(name = "pageNum",required = false, defaultValue = "1") Integer pageNum,
                @RequestParam(name = "pageSize",required = false, defaultValue = "10") Integer pageSize);

        @GetMapping("/export")
         void export(HttpServletResponse response) throws IOException;

        @GetMapping("/upload/{fileId}")
         Result upload(@PathVariable("fileId") String fileId);
    }

    @FeignClient(name = "product-service",path = "/api/orders",fallback = OrdersServiceClientFallback.class)
    public interface OrdersServiceClient {
        @PostMapping
         Result<?> save(@RequestBody Orders order);

        @PutMapping
         Result<?> update(@RequestBody Orders order);

        @DeleteMapping("/{id}")
         Result<?> delete(@PathVariable("id") Long id);

        @GetMapping("/{id}")
         Result<?> findById(@PathVariable("id") Long id);

        @GetMapping
         Result<?> findAll();
        @GetMapping("/page")
         Result<?> findPage(@RequestParam(name = "name",required = false, defaultValue = "") String name,
                            @RequestParam(name = "pageNum",required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize",required = false, defaultValue = "10") Integer pageSize);

        @GetMapping("/export")
         void export(HttpServletResponse response) throws IOException;

        @GetMapping("/upload/{fileId}")
         Result<?> upload(@PathVariable("fileId") String fileId);

    }

    @FeignClient(name = "product-service",path = "/api/permission",fallback = PermissionServiceClientFallback.class)
    public interface PermissionServiceClient {
        @PostMapping
        Result<?> save(@RequestBody Permission permission);

        @PutMapping
        Result<?> update(@RequestBody Permission permission);

        @DeleteMapping("/{id}")
        Result<?> delete(@PathVariable("id") Long id);

        @GetMapping("/{id}")
        Result<Permission> findById(@PathVariable("id") Long id);

        @GetMapping
        Result<List<Permission>> findAll();

        @GetMapping("/page")
        Result<IPage<Permission>> findPage(@RequestParam(name = "name",required = false, defaultValue = "") String name,
                                           @RequestParam(name = "pageNum",required = false, defaultValue = "1") Integer pageNum,
                                           @RequestParam(name = "pageSize",required = false, defaultValue = "10") Integer pageSize);

        @PostMapping("/getByRoles")
        Result<List<Permission>> getByRoles(@RequestBody List<Role> roles);
    }

    @FeignClient(name = "product-service",path ="/api/role",fallback = RoleServiceClientFallback.class)
    public interface RoleServiceClient {
        @PostMapping
         Result<?> save(@RequestBody Role role);

        @PutMapping
         Result<?> update(@RequestBody Role role);

        @DeleteMapping("/{id}")
         Result<?> delete(@PathVariable("id") Long id);

        @GetMapping("/{id}")
         Result<?> findById(@PathVariable("id") Long id);

        @GetMapping
         Result<?> findAll();
        @GetMapping("/page")
         Result<?> findPage(@RequestParam(name = "name",required = false, defaultValue = "") String name,
                            @RequestParam(name = "pageNum",required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize",required = false, defaultValue = "10") Integer pageSize);

    }

    @FeignClient(name = "product-service",path = "/api/user",fallback = UserServiceClientFallback.class)
    public interface UserServiceClient {
        @PostMapping
         Result<?> save(@RequestBody User user);

        @PutMapping
         Result<?> update(@RequestBody User user);

        @DeleteMapping("/{id}")
         Result<?> delete(@PathVariable("id") Long id);

        @GetMapping("/{id}")
         Result<?> findById(@PathVariable("id") Long id);

        @GetMapping
         Result<?> findAll();
        @GetMapping("/page")
         Result<?> findPage(@RequestParam(name = "name",required = false, defaultValue = "") String name,
                            @RequestParam(name = "pageNum",required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize",required = false, defaultValue = "10") Integer pageSize);

        @GetMapping("/export")
         void export(HttpServletResponse response) throws IOException;

        @GetMapping("/upload/{fileId}")
         Result<?> upload(@PathVariable("fileId") String fileId);
    }

}

