package com.blogWebAapp.blogServer.services;

import com.blogWebAapp.blogServer.entities.Post;

import java.util.List;

public interface PostService {
    Post savePost(Post post);

    List<Post> getAllPosts();

    Post getPostById(Long id);

    void likePost(Long id);

    List<Post> searchByName(String name);
}
