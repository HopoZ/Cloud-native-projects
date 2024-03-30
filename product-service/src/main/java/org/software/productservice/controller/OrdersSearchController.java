package org.software.productservice.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.software.productservice.common.Result;
import org.software.productservice.entity.OrdersSearch;
import org.software.productservice.entity.User;
import org.software.productservice.exception.CustomException;
import org.software.productservice.service.OrdersSearchService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ordersSearch")
public class OrdersSearchController {
    @Resource
    private OrdersSearchService ordersSearchService;
    @Resource
    private HttpServletRequest request;

    public User getUser() {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException("-1", "请登录");
        }
        return user;
    }

    @PostMapping
    public Result<?> save(@RequestBody OrdersSearch ordersSearch) {
        return Result.success(ordersSearchService.save(ordersSearch));
    }

    @PutMapping
    public Result<?> update(@RequestBody OrdersSearch ordersSearch) {
        return Result.success(ordersSearchService.updateById(ordersSearch));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        ordersSearchService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(ordersSearchService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(ordersSearchService.list());
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<OrdersSearch> query = Wrappers.<OrdersSearch>lambdaQuery().orderByDesc(OrdersSearch::getId);
        if (StrUtil.isNotBlank(name)) {
            query.like(OrdersSearch::getName, name);
        }
        return Result.success(ordersSearchService.page(new Page<>(pageNum, pageSize), query));
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {

        List<Map<String, Object>> list = CollUtil.newArrayList();

        List<OrdersSearch> all = ordersSearchService.list();
        for (OrdersSearch obj : all) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("订单序号", obj.getId());
            row.put("订单名称", obj.getName());

            list.add(row);
        }

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("订单查询信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(System.out);
    }

    @GetMapping("/upload/{fileId}")
    public Result<?> upload(@PathVariable String fileId) {
        String basePath = System.getProperty("user.dir") + "/src/main/resources/static/file/";
        List<String> fileNames = FileUtil.listFileNames(basePath);
        String file = fileNames.stream().filter(name -> name.contains(fileId)).findAny().orElse("");
        List<List<Object>> lists = ExcelUtil.getReader(basePath + file).read(1);
        List<OrdersSearch> saveList = new ArrayList<>();
        for (List<Object> row : lists) {
            OrdersSearch obj = new OrdersSearch();
            obj.setName((String) row.get(1));

            saveList.add(obj);
        }
        ordersSearchService.saveBatch(saveList);
        return Result.success();
    }

}
