package com.geek.store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.geek.store.model.UserModel;
import com.geek.store.repository.UserRepository;

@Component
public class AdminInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Create admin user if it doesn't exist
        if (userRepository.findByUsername("admin") == null) {
            UserModel adminUser = new UserModel();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin"));
            adminUser.setEmail("admin@store.com");
            adminUser.setRole("ROLE_ADMIN");
            userRepository.save(adminUser);
        }
    }
}
