package com.lifeledger.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.lifeledger.dto.HistoryResponse;
import com.lifeledger.service.HistoryService;

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = "http://localhost:5173")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(
            HistoryService historyService) {

        this.historyService = historyService;
    }

    @GetMapping
    public ResponseEntity<List<HistoryResponse>> getHistory(
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                historyService.getHistory(email));
    }
}