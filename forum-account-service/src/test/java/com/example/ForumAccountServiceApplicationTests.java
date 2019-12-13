package com.example;

import com.zykj.forum.ForumAccountServiceApplication;
import com.zykj.forum.entity.Comment;
import com.zykj.forum.entity.CommentParentChild;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = ForumAccountServiceApplication.class)
class ForumAccountServiceApplicationTests {
    @Resource
    CommentRepository commentRepository;
    @Autowired
    CommentParentChildRepository commentParentChildRepository;
    @Test
    void contextLoads() {
        final Comment sjh = commentRepository.save(new Comment(null, "sjh", new Date(), null, null));
        final Comment sjh2 = commentRepository.save(new Comment(null, "sjh", new Date(), null, null));
        commentParentChildRepository.save(new CommentParentChild(null,sjh,sjh2));
        final List<Comment> getchilds = commentParentChildRepository.getchilds(sjh.getId());
        final CommentParentChild byParentEquals = commentParentChildRepository.findByParentEquals(sjh);
        final Comment child = byParentEquals.getChild();
        System.out.println(getchilds);
        System.out.println(child.getId());
    }
}
