package com.lwk.blog.pojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author lwk
 * @date 2024-07-17 19:41:36
 * @deprecated 角色认证表
 */

@Data
public class Authorities implements GrantedAuthority {

    /**
     * 主键
     */
    private Integer authorityId;

    /**
     * 角色
     */
    private String authority;

    /**
     * 用户名
     */
    private String username;

}
