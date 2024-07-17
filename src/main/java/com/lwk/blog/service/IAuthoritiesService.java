package com.lwk.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lwk.blog.pojo.Authorities;

import java.util.List;

/**
 * @author lwk
 * @date 2024-07-17 19:43:57
 * @deprecated 描述
 */
public interface IAuthoritiesService extends IService<Authorities> {
    List<Authorities> getAuthoritiesForUser(String username);
}
