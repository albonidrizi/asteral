package com.nasa.asteral.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nasa.asteral.model.db.MyUser;
import com.nasa.asteral.repository.MyUserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final MyUserRepository myUserRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletRequest request,
            Model model) {

        // Check if username already exists
        if (myUserRepository.findUserByUsernameIgnoreCase(username).isPresent()) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }

        // Create new user with encoded password
        MyUser newUser = new MyUser();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRole("USER");

        myUserRepository.save(newUser);

        try {
            request.login(username, password);
        } catch (ServletException e) {
            model.addAttribute("error", "Registration successful but auto-login failed.");
            return "login";
        }

        return "redirect:/";
    }
}
