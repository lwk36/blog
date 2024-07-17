package com.lwk.blog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.lang.NonNull;

/**
 * @author lwk
 * @date 2024-07-16 21:05:42
 * @deprecated 文章实体
 */
@TableName("post")
@Data
public class Post {

    /**
     * 主键
     */
    @TableId(value = "post_id", type = IdType.AUTO)
    private Integer postId;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 作者ID
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 创建时间
     */
    @TableField("created")
    private String created;

    /**
     * 更新时间
     */
    @TableField("last_modified")
    private String lastModified;

}
