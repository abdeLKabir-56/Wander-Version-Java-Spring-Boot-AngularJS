package com.blogWebAapp.blogServer.dto;

import lombok.Data;

@Data
public class signInRequest {
    private String email;
    private String password;
}
