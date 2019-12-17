package com.zykj.forum.controller;

import com.zykj.forum.RestResult;
import com.zykj.forum.dto.PostsDto;
import com.zykj.forum.entity.Posts;
import com.zykj.forum.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
public class PostsController {

    @Autowired
    PostsService postsService;

    @PostMapping("/putposts")
    public String putPosts(@Valid  PostsDto postsDto){
        postsService.publishPosts(postsDto);
        return "ok";
    }

    @GetMapping("/posts")
    public Posts getPosts(@RequestParam("pid") Long postsId){
        final Posts posts = postsService.findPostsById(postsId).get();
        return posts;
    }

    @PostMapping("/update")
    public RestResult updatePosts(@Valid PostsDto postsDto, @RequestParam("pid") Long postsId){
        final RestResult restResult = new RestResult();
        if(postsService.updatePosts(postsDto,postsId))
            restResult.setCode("ok");
        else{
            restResult.setCode("no");
            restResult.setMsg("没有这篇文章");
        }
        return restResult;
    }

    @GetMapping("/del")
    public RestResult delPosts(@RequestParam("pid") Long postsId){
        final RestResult restResult = new RestResult();
        if(postsService.deletePosts(postsId))
            restResult.setCode("ok");
        else{
            restResult.setCode("no");
            restResult.setMsg("没有这篇文章");
        }
        return restResult;
    }
}
