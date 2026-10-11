package com.lifeledger.dto;

import java.time.LocalDateTime;

public class HistoryResponse {

    private Long id;
    private Long itemId;
    private String title;
    private String category;
    private String action;
    private LocalDateTime timestamp;

    public HistoryResponse(
            Long id,
            Long itemId,
            String title,
            String category,
            String action,
            LocalDateTime timestamp) {

        this.id = id;
        this.itemId = itemId;
        this.title = title;
        this.category = category;
        this.action = action;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public Long getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getAction() {
        return action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}