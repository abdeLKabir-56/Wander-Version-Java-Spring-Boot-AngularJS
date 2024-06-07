package com.blogWebAapp.blogServer.services;

import com.blogWebAapp.blogServer.dto.RefreshTokenRequest;
import com.blogWebAapp.blogServer.dto.jwtAuthentificationResponse;
import com.blogWebAapp.blogServer.dto.signInRequest;
import com.blogWebAapp.blogServer.dto.SignUpRequest;
import com.blogWebAapp.blogServer.entities.User;
import com.blogWebAapp.blogServer.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;
    
    public User singUp(SignUpRequest signUpRequest) {
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setImage(signUpRequest.getImage());
        user.setName(signUpRequest.getName());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setUsername(signUpRequest.getUsername());
        return userRepository.save(user);
    }
    public jwtAuthentificationResponse signIn(signInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);
        jwtAuthentificationResponse jwtAuthentificationResponse = new jwtAuthentificationResponse();
        jwtAuthentificationResponse.setAccessToken(jwtToken);
        jwtAuthentificationResponse.setRefreshToken(refreshToken);
        return jwtAuthentificationResponse;
    }
    public jwtAuthentificationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUsername(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            String newToken = jwtService.generateToken(user);
            jwtAuthentificationResponse jwtAuthenticationResponse = new jwtAuthentificationResponse();
            jwtAuthenticationResponse.setAccessToken(newToken);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        } else {
            return null;
        }
    }
}
