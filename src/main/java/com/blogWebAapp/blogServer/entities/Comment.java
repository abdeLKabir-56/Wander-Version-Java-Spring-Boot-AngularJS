package com.blogWebAapp.blogServer.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String postedBy;
    private String content;
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;
}
