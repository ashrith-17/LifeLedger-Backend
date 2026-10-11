package com.lifeledger.dto;

public class RegisterResponse {

    private String message;
    private Long userId;
    private String name;
    private String email;

    public RegisterResponse(
            String message,
            Long userId,
            String name,
            String email) {

        this.message = message;
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}