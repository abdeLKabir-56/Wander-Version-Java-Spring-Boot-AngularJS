package com.blogWebAapp.blogServer.dto;

import lombok.Data;

@Data
public class SignUpRequest {


    private String email;
    private String image;
    private String name;
    private String password;
    private String username;
}
