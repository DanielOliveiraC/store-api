package com.geek.store.controller;

import com.geek.store.model.UserModel;
import com.geek.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new UserModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserModel user, 
                             BindingResult result, 
                             RedirectAttributes redirectAttributes) {
        try {
            // Check if username exists
            if (userService.existsByUsername(user.getUsername())) {
                result.rejectValue("username", "error.user", "Username already exists");
            }

            // Check if email exists
            if (userService.existsByEmail(user.getEmail())) {
                result.rejectValue("email", "error.user", "Email already exists");
            }

            if (result.hasErrors()) {
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
                redirectAttributes.addFlashAttribute("user", user);
                return "redirect:/users/register";
            }

            userService.createUser(user);
            return "redirect:/users/login?success";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error registering user: " + e.getMessage());
            return "redirect:/users/register?error";
        }
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            UserModel user = userService.findByUsername(auth.getName());
            if (user != null) {
                model.addAttribute("user", user);
                return "profile";
            }
        }
        return "redirect:/users/login";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/users/login?logout";
    }
}
