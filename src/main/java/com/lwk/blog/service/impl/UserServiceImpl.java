package com.lwk.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwk.blog.mapper.UserMapper;
import com.lwk.blog.pojo.Users;
import com.lwk.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author lwk
 * @date 2024-07-16 22:57:01
 * @deprecated 描述
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, Users> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Optional<Users> findByUsername(String username) {
        Users users = this.getBaseMapper().findByUsername(username);
        if (users != null) {
            return Optional.of(users);
        }
        return Optional.empty();
    }
}
