package com.zykj.forum.service;


import com.zykj.forum.dto.CommentDto;
import com.zykj.forum.entity.Comment;
import com.zykj.forum.entity.CommentParentChild;
import com.zykj.forum.entity.Posts;
import com.zykj.forum.entity.User;
import com.zykj.forum.repostitory.ComParChildRepository;
import com.zykj.forum.repostitory.CommentRepository;
import com.zykj.forum.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    Logger logger= LoggerFactory.getLogger(CommentService.class);
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ComParChildRepository comParChildRepository;

    public Comment findCommentById(Long id){
        return commentRepository.findById(id).get();
    }

    public Page<Comment> findCommentByParent(Long parentId,Pageable pageable){
        if(!commentRepository.existsById(parentId))
            return Page.empty();
        final Comment comment = new Comment();
        comment.setId(parentId);
        final Page<CommentParentChild> allByParentEquals = comParChildRepository.findAllByParentEquals(comment, pageable);
        return this.generatePage(allByParentEquals);
    }
    private Page<Comment> generatePage(Page<CommentParentChild> allByParentEquals){
        if(allByParentEquals.equals(Page.empty()))
            return Page.empty();
        List<Comment> ls=new ArrayList<>();
        allByParentEquals.forEach(commentParentChild -> {
            final Comment child = commentParentChild.getChild();
            child.setUser(null);
            ls.add(child);
        });
        return new PageImpl<Comment>(ls,allByParentEquals.getPageable(),allByParentEquals.getTotalElements());
    }
    public Page<Comment> findCommentByPostId(Long id, Pageable pageable){
        final Posts posts = new Posts();
        posts.setId(id);
        return commentRepository.findAllByPostsEquals(posts,pageable);
    }

    public Page<Comment> findCommentByUserId(Long userId,Pageable pageable) {
        final User user = new User();
        user.setId(userId);
        return commentRepository.findAllByUserEquals(user,pageable);
    }

    @Transactional
    public boolean delComment(Long commentId) {
        if(!commentRepository.existsById(commentId))
            return false;
        final Comment comment = new Comment();
        comment.setId(commentId);
        dpsDel(comment);
        return true;
    }

    /**
     * 删除关系表中，引用了child 的数据
     */
    private static void delParent(ComParChildRepository comParChildRepository,Comment child){
        final Page<CommentParentChild> allByChildEquals = comParChildRepository.findAllByChildEquals(child, Pageable.unpaged());
        allByChildEquals.forEach(commentParentChild -> {
            comParChildRepository.deleteById(commentParentChild.getId());
        });
    }
    //递归删除子评论
    private void dpsDel(Comment comment){
        delParent(comParChildRepository,comment);
        final Page<CommentParentChild> allByParentEquals = comParChildRepository.findAllByParentEquals(comment, Pageable.unpaged());
        allByParentEquals.forEach(commentParentChild -> {
            final Comment child = commentParentChild.getChild();
            dpsDel(child);
        });
        commentRepository.deleteById(comment.getId());
    }

    @Transactional
    public void saveComment(CommentDto commentDto) {
        final Comment save = save(commentDto,true);
        if(logger.isInfoEnabled())
            logger.info("sava ... {}",save);
    }
    @Transactional
    public void saveComment(CommentDto commentDto,Long parentId) {
        final Comment save = save(commentDto,false);
        final Comment comment = new Comment();
        comment.setId(parentId);
        comParChildRepository.save(new CommentParentChild(null, comment,save));
    }


    private Comment save(CommentDto commentDto,boolean is1comment){
        User user =new User();
        user.setId(commentDto.getUid());
        Posts posts = null;
        if(is1comment){
            posts = new Posts();
            posts.setId(commentDto.getPid());
        }
        return commentRepository.save(new Comment(null,commentDto.getTxt(), DateUtil.now(), user, posts));
    }
}
