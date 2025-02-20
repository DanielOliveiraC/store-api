package com.geek.store.controller;

import com.geek.store.model.UserModel;
import com.geek.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserModel registrar(@RequestBody UserModel user) {
        return userService.save(user);
    }
}