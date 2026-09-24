package com.iam.controller;

import com.iam.dto.LoginRequest;
import com.iam.dto.LoginResponse;
import com.iam.dto.RegisterRequest;
import com.iam.service.AuthenticationService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authenticationService.login(request);
    }

    @PostMapping("/register")
    public String register(
            @Valid @RequestBody RegisterRequest request) {

        return authenticationService.register(request);
    }

    @GetMapping("/profile")
    public String profile() {
        return "User profile";
    }
}