package com.blogWebAapp.blogServer.services;

import com.blogWebAapp.blogServer.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();
    List<User> getAllUsers();

    User getUserById(Long userId);
}
