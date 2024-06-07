package com.blogWebAapp.blogServer;

import com.blogWebAapp.blogServer.entities.Role;
import com.blogWebAapp.blogServer.entities.User;
import com.blogWebAapp.blogServer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BlogServerApplication implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(BlogServerApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
			if (userRepository.findByRole(Role.ADMIN) == null) {
				User admin = new User();
				admin.setUsername("admin");
				admin.setName("Admin");
				admin.setEmail("admin@gmail.com");
				admin.setRole(Role.ADMIN); // Assuming you have a role field in your User entity
				admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
				userRepository.save(admin);
			}

	}
}
