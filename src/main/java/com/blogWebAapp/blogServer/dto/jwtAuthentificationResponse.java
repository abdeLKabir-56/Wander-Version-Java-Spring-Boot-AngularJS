package com.blogWebAapp.blogServer.dto;

import lombok.Data;

@Data
public class jwtAuthentificationResponse {
    private String accessToken;
    private String refreshToken;
}
