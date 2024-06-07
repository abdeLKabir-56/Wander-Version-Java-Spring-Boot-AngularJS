package com.blogWebAapp.blogServer.controllers;

import com.blogWebAapp.blogServer.entities.Comment;
import com.blogWebAapp.blogServer.entities.Post;
import com.blogWebAapp.blogServer.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("comments/create")
    public ResponseEntity<?> createComment(@RequestParam Long postId, @RequestParam String postedBy, @RequestParam String content) {
        try {
            return ResponseEntity.ok(commentService.createComment(postId,postedBy,content));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("comments/{postId}")
    public ResponseEntity<List<Comment>>getAllComments(@PathVariable Long postId)
    {
        try {
            return ResponseEntity.ok(commentService.getCommentByPostId(postId));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
