package com.blogWebAapp.blogServer.repositories;

import com.blogWebAapp.blogServer.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByNameContaining(String name);
}
