package com.lwk.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwk.blog.pojo.Users;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lwk
 * @date 2024-07-16 22:57:28
 * @deprecated 描述
 */
@Mapper
public interface UserMapper extends BaseMapper<Users> {

    Users findByUsername(String username);
}
