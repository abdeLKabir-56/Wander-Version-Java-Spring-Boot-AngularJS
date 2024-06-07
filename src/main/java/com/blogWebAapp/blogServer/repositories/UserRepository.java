package com.blogWebAapp.blogServer.repositories;

import com.blogWebAapp.blogServer.entities.Role;
import com.blogWebAapp.blogServer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    User findByRole(Role role);
}
