package org.software.productclient.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.software.productclient.common.handler.ListHandler;

import java.util.List;

@Data
@TableName(value = "t_user", autoResultMap = true)
public class User extends Model<User> {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String nickName;

    private String email;

    private String phone;

    private String avatar;
    private String address;
    private Integer age;

    /**
     * 权限
     */
    @TableField(typeHandler = ListHandler.class)
    private List<Long> role;

    @TableField(exist = false)
    private List<Permission> permission;

}
