package com.lwk.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwk.blog.mapper.PostMapper;
import com.lwk.blog.pojo.Post;
import com.lwk.blog.service.IPostService;
import org.springframework.stereotype.Service;

/**
 * @author lwk
 * @date 2024-07-17 19:58:16
 * @deprecated 文章服务实现类
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {
}
