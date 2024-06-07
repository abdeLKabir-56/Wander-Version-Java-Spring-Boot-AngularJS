package com.blogWebAapp.blogServer.controllers;

import com.blogWebAapp.blogServer.dto.RefreshTokenRequest;
import com.blogWebAapp.blogServer.dto.SignUpRequest;
import com.blogWebAapp.blogServer.dto.jwtAuthentificationResponse;
import com.blogWebAapp.blogServer.dto.signInRequest;
import com.blogWebAapp.blogServer.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest) {
        try {
            authenticationService.singUp(signUpRequest);
            return ResponseEntity.ok(new String[]{"The user is registered successfully!"});
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PostMapping("/signin")
    public ResponseEntity<jwtAuthentificationResponse> signup(@RequestBody signInRequest signInRequest) {
            return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<jwtAuthentificationResponse> refresh(@RequestBody RefreshTokenRequest  refreshTokenRequest ) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
