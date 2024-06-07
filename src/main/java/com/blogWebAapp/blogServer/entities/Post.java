package com.blogWebAapp.blogServer.entities;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;
import java.util.List;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 5000)
    private String content;
    /*@ManyToOne
    @JoinColumn(name = "author_id", nullable = false)*/
    private String postedBy;
    private String image;
    private Date date;
    private int likeCount;
    private int viewCount;
    private List<String>tags;
}
