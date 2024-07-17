package com.lwk.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lwk.blog.pojo.Users;

import java.util.Optional;

/**
 * @author lwk
 * @date 2024-07-16 22:56:41
 * @deprecated 描述
 */
public interface IUserService extends IService<Users> {
    Optional<Users> findByUsername(String username);
}
