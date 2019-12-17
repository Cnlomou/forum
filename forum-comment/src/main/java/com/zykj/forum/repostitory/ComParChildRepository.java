package com.zykj.forum.repostitory;

import com.zykj.forum.entity.Comment;
import com.zykj.forum.entity.CommentParentChild;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComParChildRepository extends JpaRepository<CommentParentChild,Long> {
    Page<CommentParentChild> findAllByChildEquals(Comment comment, Pageable pageable);
    Page<CommentParentChild> findAllByParentEquals(Comment comment, Pageable pageable);
    boolean existsByParentEquals(Comment parent);
    boolean existsByChildEquals(Comment child);
}
