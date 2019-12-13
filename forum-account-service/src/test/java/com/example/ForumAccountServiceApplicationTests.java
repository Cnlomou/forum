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

    @Test
    void contextLoads() {
    }
}
