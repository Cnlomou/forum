package com.zykj.forum.service;

import com.zykj.forum.dto.PostsDto;
import com.zykj.forum.entity.Posts;
import com.zykj.forum.entity.Subject;
import com.zykj.forum.entity.User;
import com.zykj.forum.repository.PostsRepository;
import com.zykj.forum.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@Service
public class PostsService {

    @Autowired
    PostsRepository postsRepository;

    @Transactional
    public void publishPosts(PostsDto postsDto){
        final User user = new User();
        final Subject subject = new Subject();
        user.setId(postsDto.getUserId());
        subject.setId(postsDto.getSubjectId());
        final Date now = DateUtil.now();
        postsRepository.save(new Posts(null,postsDto.getTitle(),postsDto.getContent(),0,0, now,now,subject,user));
    }

    public Optional<Posts> findPostsById(Long id){
        return postsRepository.findById(id);
    }

    @Transactional
    public boolean updatePosts(PostsDto postsDto,Long postsId){
        if(!postsRepository.existsById(postsId))
            return false;
        final Posts posts = new Posts();
        posts.setId(postsId);
        posts.setTitle(postsDto.getTitle());
        posts.setContent(postsDto.getContent());
        posts.setUpdate_at(DateUtil.now());
        postsRepository.updatePosts(posts);
        return true;
    }
    @Transactional
    public boolean deletePosts(Long postsId) {
        if(!postsRepository.existsById(postsId))
            return false;
        postsRepository.deleteById(postsId);
        return true;
    }
}
