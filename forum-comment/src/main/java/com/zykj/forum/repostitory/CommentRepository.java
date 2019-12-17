package com.zykj.forum.repostitory;

import com.zykj.forum.entity.Comment;
import com.zykj.forum.entity.Posts;
import com.zykj.forum.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query("select c.id,c.content,c.create_at from Comment c where c.posts=:posts")
    Page<Comment> findAllByPostsEquals(Posts posts, Pageable pageable);
    @Query("select c.id,c.content,c.create_at from Comment c where c.user=:user")
    Page<Comment> findAllByUserEquals(User user,Pageable pageable);
}
