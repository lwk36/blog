package com.lwk.blog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lwk
 * @date 2024-07-16 21:02:12
 * @deprecated 描述
 */
@TableName("users")
@Data
public class Users {

    /**
     * 主键
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    /**
     * 用户名
     */
    @TableField("username")
    private String username;
    /**
     * 密码
     */
    @TableField("password")
    private String password;
    /**
     * 邮箱
     */
    @TableField("email")
    private String email;
    /**
     * 创建时间
     */
    @TableField("created")
    private String created;
    /**
     * 更新时间
     */
    @TableField("last_modified")
    private String lastModified;

}
