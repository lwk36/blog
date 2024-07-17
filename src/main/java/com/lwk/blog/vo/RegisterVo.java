package com.lwk.blog.vo;

import lombok.Data;

/**
 * @author lwk
 * @date 2024-07-17 19:50:11
 * @deprecated 注册入参
 */
@Data
public class RegisterVo {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;
}
