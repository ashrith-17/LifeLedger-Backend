package com.lifeledger.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.lifeledger.dto.DashboardResponse;
import com.lifeledger.security.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:5173")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService) {

        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<DashboardResponse> getDashboard(
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                dashboardService.getDashboard(email)
        );
    }
}