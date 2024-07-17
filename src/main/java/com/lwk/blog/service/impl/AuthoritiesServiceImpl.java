package com.lwk.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwk.blog.mapper.AuthoritiesMapper;
import com.lwk.blog.pojo.Authorities;
import com.lwk.blog.service.IAuthoritiesService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lwk
 * @date 2024-07-17 19:44:55
 * @deprecated 描述
 */
@Service
public class AuthoritiesServiceImpl extends ServiceImpl<AuthoritiesMapper, Authorities> implements IAuthoritiesService {
    @Override
    public List<Authorities> getAuthoritiesForUser(String username) {
        List<Authorities> authoritiesForUser = this.getBaseMapper().getAuthoritiesForUser(username);
        return authoritiesForUser;
    }
}
