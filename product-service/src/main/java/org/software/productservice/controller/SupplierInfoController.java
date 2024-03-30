package org.software.productservice.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.software.productservice.common.Result;
import org.software.productservice.entity.SupplierInfo;
import org.software.productservice.entity.User;
import org.software.productservice.exception.CustomException;
import org.software.productservice.service.SupplierInfoService;
import org.software.productservice.service.UserService;
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
@RequestMapping("/api/supplierInfo")
public class SupplierInfoController {
    @Resource
    private SupplierInfoService supplierInfoService;

    @Resource
    private UserService userService;
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
    public Result<?> save(@RequestBody SupplierInfo supplierInfo) {
        return Result.success(supplierInfoService.save(supplierInfo));
    }

    @PutMapping
    public Result<?> update(@RequestBody SupplierInfo supplierInfo) {
        return Result.success(supplierInfoService.updateById(supplierInfo));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        supplierInfoService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(supplierInfoService.getById(id));
    }

// todo
    @GetMapping
    public Result<List<User>> findAll() {
        return Result.success(userService.list(Wrappers.<User>lambdaQuery().ne(User::getUsername, "admin")));
    }
// todo
    @GetMapping("/page")
    public Result<IPage<User>> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                        @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                        @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery().ne(User::getUsername, "admin").eq(User::getRole,"[7]").like(User::getUsername, name).orderByDesc(User::getId);
        return Result.success(userService.page(new Page<>(pageNum, pageSize), wrapper));
    }


    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {

        List<Map<String, Object>> list = CollUtil.newArrayList();

        List<SupplierInfo> all = supplierInfoService.list();
        for (SupplierInfo obj : all) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("地址", obj.getAddress());
            row.put("年龄", obj.getAge());
            row.put("邮箱", obj.getEmail());
            row.put("", obj.getId());
            row.put("手机号", obj.getPhone());
            row.put("用户名", obj.getUsername());

            list.add(row);
        }

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("供应商信息信息", "UTF-8");
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
        List<SupplierInfo> saveList = new ArrayList<>();
        for (List<Object> row : lists) {
            SupplierInfo obj = new SupplierInfo();
            obj.setAddress((String) row.get(1));
            obj.setAge(Integer.valueOf((String) row.get(2)));
            obj.setEmail((String) row.get(3));
            obj.setPhone((String) row.get(4));
            obj.setUsername((String) row.get(5));

            saveList.add(obj);
        }
        supplierInfoService.saveBatch(saveList);
        return Result.success();
    }

}
