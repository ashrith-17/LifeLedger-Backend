package com.lifeledger.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.lifeledger.dto.ReminderResponse;
import com.lifeledger.service.ReminderService;

@RestController
@RequestMapping("/api/reminders")
@CrossOrigin(origins = "http://localhost:5173")
public class ReminderController {

    private final ReminderService reminderService;

    public ReminderController(
            ReminderService reminderService) {

        this.reminderService = reminderService;
    }

    @GetMapping
    public ResponseEntity<List<ReminderResponse>> getReminders(
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                reminderService.getReminders(email));
    }
}