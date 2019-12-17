package com.zykj.forum;

import com.zykj.forum.entity.Comment;
import com.zykj.forum.entity.CommentParentChild;
import com.zykj.forum.repostitory.ComParChildRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.List;

@SpringBootTest(classes = ForumCommentApplication.class)
public class ForumCommentTest {
    @Autowired
    ComParChildRepository comParChildRepository;
    @Test
    public void pageTest(){
        final PageRequest pageable = PageRequest.of(2,5);
        final Page<CommentParentChild> allByChildEquals =  comParChildRepository.findAllByChildEquals(new Comment(1L, null, null, null, null), pageable);
        System.out.println(allByChildEquals);
    }
}
