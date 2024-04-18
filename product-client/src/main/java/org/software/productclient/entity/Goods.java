package org.software.productclient.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;


@Data
@TableName("t_goods")
public class Goods extends Model<Goods> {
    /**
      * 主键
      */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
      * 入库时间 
      */
    private String intime;

    /**
      * 货品名称 
      */
    private String name;

    /**
      * 货品数量 
      */
    private Long number;

    /**
      * 单价 
      */
    private Long price;

}