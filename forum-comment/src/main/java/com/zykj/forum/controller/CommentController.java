package com.zykj.forum.controller;

import com.netflix.discovery.converters.Auto;
import com.zykj.forum.RestResult;
import com.zykj.forum.dto.CommentDto;
import com.zykj.forum.dto.PageDto;
import com.zykj.forum.entity.Comment;
import com.zykj.forum.service.CommentService;
import com.zykj.forum.util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;
    @GetMapping("/comment/siglon")
    public Comment getOneComment(@RequestParam("cid") Long commentId){
        return commentService.findCommentById(commentId);
    }
    @GetMapping("/comment")
    public Page<Comment> getComment(@RequestParam(name = "pid",required = false) Long postsId,
                                 @RequestParam(name = "uid",required = false) Long userId,
                                 @RequestParam(name = "parid",required = false) Long parentId,
                                 @Valid PageDto pageDto){
        Page<Comment> commentByUserId=Page.empty();
        if(postsId!=null) {
            commentByUserId= commentService.findCommentByPostId(postsId,
                    PageRequest.of(pageDto.getPno(),pageDto.getSize(),Sort.by(new Sort.Order(Sort.Direction.DESC,"id"))));
        }
        else if(userId!=null){
            commentByUserId= commentService.findCommentByUserId(userId,
                    PageRequest.of(pageDto.getPno(),pageDto.getSize(),Sort.by(new Sort.Order(Sort.Direction.DESC,"id"))));
        }else if(parentId!=null){
            commentByUserId=commentService.findCommentByParent(parentId,
                    PageRequest.of(pageDto.getPno(),pageDto.getSize(),Sort.by(new Sort.Order(Sort.Direction.DESC,"id"))));
        }
        return commentByUserId;
    }

    @GetMapping("/del")
    public String delComment(@RequestParam(name = "cid") Long commentId){
        return commentService.delComment(commentId)? RestUtil.ok() :RestUtil.no();
    }

    @PostMapping("/put")
    public String saveComment(@Valid CommentDto commentDto,@RequestParam(name = "parid",required = false) Long parentId){
        if(parentId!=null)
            commentService.saveComment(commentDto,parentId);
        commentService.saveComment(commentDto);
        return RestUtil.ok();
    }
}
