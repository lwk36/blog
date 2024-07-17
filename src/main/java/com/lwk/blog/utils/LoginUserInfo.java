package com.lwk.blog.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @author lwk
 * @date 2024-07-17 20:11:05
 * @deprecated 获取当前登陆人信息
 */
public final class LoginUserInfo {

    /**
     * 获取当前登录的用户信息。
     *
     * @return 当前登录的UserDetails对象，如果没有登录则返回null。
     */
    public static CustomUserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (CustomUserDetails) authentication.getPrincipal();
        }
        return null;
    }

    public static Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return userDetails.getUserId();
        }
        return null;
    }

    /**
     * 获取当前登录用户的用户名。
     *
     * @return 用户名，如果没有登录则返回null。
     */
    public static String getUsername() {
        UserDetails userDetails = getCurrentUser();
        return userDetails != null ? userDetails.getUsername() : null;
    }

//    public static Integer getUserId() {
//        return jdbcTemplate.queryForObject("SELECT user_id FROM user WHERE username = ?", Integer.class, getUsername());
//    }

    /**
     * 获取当前登录用户的权限列表。
     *
     * @return 权限列表，如果没有登录则返回空集合。
     */
    public static Collection<? extends GrantedAuthority> getAuthorities() {
        UserDetails userDetails = getCurrentUser();
        return userDetails != null ? userDetails.getAuthorities() : Collections.emptyList();
    }

    /**
     * 检查当前用户是否拥有给定的角色。
     *
     * @param role 角色名称。
     * @return 如果当前用户拥有该角色，则返回true；否则返回false。
     */
    public static boolean hasRole(String role) {
        for (GrantedAuthority authority : getAuthorities()) {
            if (role.equals(authority.getAuthority())) {
                return true;
            }
        }
        return false;
    }
}
