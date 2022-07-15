package com.neurowvu.rehabilitationapp.controllers;

import com.neurowvu.rehabilitationapp.security.RegistrationForm;
import com.neurowvu.rehabilitationapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/success")
    public String success(){
        return "success";
    }


    @GetMapping
    public String registerForm(){
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        //userRepository.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
