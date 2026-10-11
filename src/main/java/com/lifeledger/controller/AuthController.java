package com.lifeledger.controller;

import com.lifeledger.dto.LoginRequest;
import com.lifeledger.dto.LoginResponse;
import com.lifeledger.dto.RegisterRequest;
import com.lifeledger.dto.RegisterResponse;
import com.lifeledger.entity.User;
import com.lifeledger.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        User user = authService.register(request);

        RegisterResponse response =
                new RegisterResponse(
                        "Registration successful",
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response =
                authService.login(request);

        return ResponseEntity.ok(response);
    }
}