package com.lifeledger.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "LifeLedger Backend is Running";
    }

    @GetMapping("/protected")
    public String protectedEndpoint(Authentication authentication) {

        return "Welcome " + authentication.getName()
                + ". JWT authentication is working!";
    }
}