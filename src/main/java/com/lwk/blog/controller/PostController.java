package com.lwk.blog.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lwk.blog.annotation.SystemLog;
import com.lwk.blog.pojo.Post;
import com.lwk.blog.service.IPostService;
import com.lwk.blog.utils.LoginUserInfo;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lwk
 * @date 2024-07-17 19:55:19
 * @deprecated 文章控制器
 */
@RestController
public class PostController {

    @Autowired
    private IPostService postService;

    @PutMapping("/posts")
    @PreAuthorize("hasAuthority('USER')")
    @SystemLog("添加文章")
    public ResponseEntity<String> addPost(@RequestBody Post post) {
        post.setUserId(LoginUserInfo.getCurrentUserId());
        postService.save(post);
        return ResponseEntity.ok("添加成功");
    }

    @GetMapping("/posts")
    @SystemLog("根据用户id获取文章列表")
    public ResponseEntity<List<Post>> getPostsByUserId(@RequestParam("uid") String uid) {
        List<Post> list = postService.list(Wrappers.<Post>lambdaQuery()
                .eq(Post::getUserId, uid));
        return ResponseEntity.ok(list);
    }

    @GetMapping("/posts/{id}")
    @SystemLog("根据文章id获取文章")
    public ResponseEntity getPostById(@PathVariable("id") Integer id) {
        Post post = postService.getById(id);
        if (post == null){
            return ResponseEntity.badRequest().body("文章不存在");
        }
        return ResponseEntity.ok(post);
    }

    @PutMapping("/posts/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @SystemLog("根据文章id修改文章")
    public ResponseEntity<String> updatePost(@PathVariable("id") Integer id,@RequestBody Post post) {
        Post postById = postService.getById(id);
        if (postById == null){
            return ResponseEntity.badRequest().body("文章不存在");
        }else if (!postById.getUserId().equals(LoginUserInfo.getCurrentUserId())){
            return ResponseEntity.badRequest().body("没有权限");
        }else if (post.getTitle() == null || post.getContent() == null){
            return ResponseEntity.badRequest().body("标题或内容不能为空");
        }
        post.setPostId(id);
        postService.updateById(post);
        return ResponseEntity.ok("修改成功");
    }

    @DeleteMapping("/posts/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @SystemLog("根据文章id删除文章")
    public ResponseEntity<String> deletePost(@PathVariable("id") Integer id) {
        Post postById = postService.getById(id);
        if (postById == null){
            return ResponseEntity.badRequest().body("文章不存在");
        }else if (!postById.getUserId().equals(LoginUserInfo.getCurrentUserId())){
            return ResponseEntity.badRequest().body("没有权限");
        }
        postService.removeById(id);
        return ResponseEntity.ok("删除成功");
    }
}
