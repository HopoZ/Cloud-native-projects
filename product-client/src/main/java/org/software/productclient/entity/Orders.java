package org.software.productclient.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_orders")
public class Orders extends Model<Orders> {
    /**
      * 主键
      */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
      * 订单创建者 
      */
    private String creator;

    /**
      * 订单创建日期 
      */
    private Date date;

    /**
      * 订单金额 
      */
    private Integer money;

    /**
      * 订单名称 
      */
    private String name;

    /**
      * 订单内货品数量 
      */
    private Integer number;

}