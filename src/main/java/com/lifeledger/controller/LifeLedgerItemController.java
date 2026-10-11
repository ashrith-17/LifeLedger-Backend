package com.lifeledger.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.lifeledger.dto.ItemStatusResponse;
import com.lifeledger.dto.LifeLedgerItemRequest;
import com.lifeledger.dto.LifeLedgerItemResponse;
import com.lifeledger.security.LifeLedgerItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "http://localhost:5173")
public class LifeLedgerItemController {

    private final LifeLedgerItemService itemService;

    public LifeLedgerItemController(
            LifeLedgerItemService itemService) {

        this.itemService = itemService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<LifeLedgerItemResponse> createItem(
            @Valid @RequestBody LifeLedgerItemRequest request,
            Authentication authentication) {

        String email = authentication.getName();

        LifeLedgerItemResponse response =
                itemService.createItem(request, email);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<LifeLedgerItemResponse>> getMyItems(
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                itemService.getMyItems(email));
    }

    // GET STATUS
    @GetMapping("/status")
    public ResponseEntity<List<ItemStatusResponse>> getItemStatuses(
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                itemService.getItemStatuses(email));
    }

    // SEARCH
    @GetMapping("/search")
    public ResponseEntity<List<LifeLedgerItemResponse>> searchItems(
            @RequestParam String keyword,
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                itemService.searchItems(email, keyword));
    }

    // FILTER BY CATEGORY
    @GetMapping("/category")
    public ResponseEntity<List<LifeLedgerItemResponse>> filterByCategory(
            @RequestParam String category,
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                itemService.filterByCategory(email, category));
    }

    // GET SINGLE ITEM
    @GetMapping("/{id}")
    public ResponseEntity<LifeLedgerItemResponse> getItem(
            @PathVariable Long id,
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                itemService.getItem(id, email));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<LifeLedgerItemResponse> updateItem(
            @PathVariable Long id,
            @Valid @RequestBody LifeLedgerItemRequest request,
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                itemService.updateItem(
                        id,
                        request,
                        email));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(
            @PathVariable Long id,
            Authentication authentication) {

        String email = authentication.getName();

        itemService.deleteItem(id, email);

        return ResponseEntity.noContent().build();
    }
}