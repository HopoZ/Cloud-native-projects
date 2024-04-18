package org.software.productclient.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;


@Data
@TableName("t_supplierInfo")
public class SupplierInfo extends Model<SupplierInfo> {
    /**
      * 主键
      */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
      * 地址 
      */
    private String address;

    /**
      * 年龄 
      */
    private Integer age;

    /**
      * 邮箱 
      */
    private String email;

    /**
      * 手机号 
      */
    private String phone;

    /**
      * 用户名 
      */
    private String username;

}