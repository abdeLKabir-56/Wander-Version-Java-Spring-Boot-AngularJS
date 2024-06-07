package com.blogWebAapp.blogServer.services;

import com.blogWebAapp.blogServer.entities.Comment;
import com.blogWebAapp.blogServer.entities.Post;
import com.blogWebAapp.blogServer.repositories.CommentRepository;
import com.blogWebAapp.blogServer.repositories.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    public Comment createComment(Long postId, String postedBy, String content) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Comment comment = new Comment();
            comment.setContent(content);
            comment.setPostedBy(postedBy);
            comment.setCreatedAt(new Date());
            comment.setPost(optionalPost.get());
            return commentRepository.save(comment);

        } else {
            throw new EntityNotFoundException("Comment not found");
        }
    }

    public List<Comment> getCommentByPostId(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            return commentRepository.findByPostId(postId);
        } else {
            throw new EntityNotFoundException("Post not found with id: " + postId);
        }
    }
}
