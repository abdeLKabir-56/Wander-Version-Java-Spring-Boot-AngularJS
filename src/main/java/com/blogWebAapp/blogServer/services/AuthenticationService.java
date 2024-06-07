package com.blogWebAapp.blogServer.services;

import com.blogWebAapp.blogServer.dto.RefreshTokenRequest;
import com.blogWebAapp.blogServer.dto.SignUpRequest;
import com.blogWebAapp.blogServer.dto.jwtAuthentificationResponse;
import com.blogWebAapp.blogServer.dto.signInRequest;
import com.blogWebAapp.blogServer.entities.User;

public interface AuthenticationService {
    User singUp(SignUpRequest signUpRequest);
     jwtAuthentificationResponse signIn(signInRequest request);
    jwtAuthentificationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
