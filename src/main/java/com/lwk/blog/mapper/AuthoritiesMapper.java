package com.lwk.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwk.blog.pojo.Authorities;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lwk
 * @date 2024-07-17 19:45:28
 * @deprecated 描述
 */
@Mapper
public interface AuthoritiesMapper extends BaseMapper<Authorities> {

    @Select("select * from authorities where username=#{username}")
    List<Authorities> getAuthoritiesForUser(String username);
}
