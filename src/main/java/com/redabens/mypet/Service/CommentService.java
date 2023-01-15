package com.redabens.mypet.Service;

import com.redabens.mypet.Dto.CommentDto;
import com.redabens.mypet.Entity.Comment;
import com.redabens.mypet.Repository.CommentRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    public Comment saveComment(CommentDto commentDto) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto, comment);
        comment.setUser(userService.getUserById(commentDto.getUserId()));
        comment.setPost(postService.getPostById(commentDto.getPostId()));
        return commentRepo.save(comment);
    }

    public Comment updateComment(CommentDto postDto, int id) {
        Comment post = commentRepo.findById(id).get();
        BeanUtils.copyProperties(postDto, post);
        return commentRepo.save(post);
    }

    public void deleteComment(int id) {
        commentRepo.deleteById(id);
    }

}
