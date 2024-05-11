package org.software.productservice.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.software.productservice.common.Result;
import org.software.productservice.entity.Orders;
import org.software.productservice.entity.User;
import org.software.productservice.exception.CustomException;
import org.software.productservice.service.OrdersService;
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
@RequestMapping("/api/orders")
public class OrdersController {
    @Resource
    private OrdersService ordersService;
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
    public Result<?> save(@RequestBody Orders orders) {
        return Result.success(ordersService.save(orders));
    }

    @PutMapping
    public Result<?> update(@RequestBody Orders orders) {
        return Result.success(ordersService.updateById(orders));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        ordersService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(ordersService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(ordersService.list());
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                  @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Orders> query = Wrappers.<Orders>lambdaQuery().orderByDesc(Orders::getId);
        if (StrUtil.isNotBlank(name)) {
            query.like(Orders::getName, name);
        }
        return Result.success(ordersService.page(new Page<>(pageNum, pageSize), query));
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {

        List<Map<String, Object>> list = CollUtil.newArrayList();

        List<Orders> all = ordersService.list();
        for (Orders obj : all) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("订单创建者", obj.getCreator());
            row.put("订单创建日期", obj.getDate());
            row.put("订单序号", obj.getId());
            row.put("订单金额", obj.getMoney());
            row.put("订单名称", obj.getName());
            row.put("订单内货品数量", obj.getNumber());

            list.add(row);
        }

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("创建订单信息", "UTF-8");
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
        List<Orders> saveList = new ArrayList<>();
        for (List<Object> row : lists) {
            Orders obj = new Orders();
            obj.setCreator((String) row.get(1));
            obj.setDate(DateUtil.parseDateTime((String) row.get(2)));
            obj.setMoney(Integer.valueOf((String) row.get(3)));
            obj.setName((String) row.get(4));
            obj.setNumber(Integer.valueOf((String) row.get(5)));

            saveList.add(obj);
        }
        ordersService.saveBatch(saveList);
        return Result.success();
    }

}
