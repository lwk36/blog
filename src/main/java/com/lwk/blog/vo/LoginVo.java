package com.lwk.blog.vo;

import lombok.Data;

/**
 * @author lwk
 * @date 2024-07-17 19:48:57
 * @deprecated 登录入参
 */
@Data
public class LoginVo {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
