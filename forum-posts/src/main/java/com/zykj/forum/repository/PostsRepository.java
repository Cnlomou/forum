package com.zykj.forum.repository;

import com.zykj.forum.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Posts,Long> {
    @Query("update Posts p set p.title=:#{#posts.title} ,p.content=:#{#posts.content},p.update_at=:#{posts.update_at} where p.id=:#{#posts.id}")
    void updatePosts(Posts posts);
}
