package com.blogWebAapp.blogServer.services;


import com.blogWebAapp.blogServer.entities.Comment;

import java.util.List;

public interface CommentService  {
    Comment createComment(Long postId,String postedBy,String content);
    public List<Comment> getCommentByPostId(Long postId);
}
