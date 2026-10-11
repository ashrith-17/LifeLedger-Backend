package com.lifeledger.dto;

public class ItemStatusResponse {

    private Long id;
    private String title;
    private String category;
    private String status;
    private long daysRemaining;

    public ItemStatusResponse(
            Long id,
            String title,
            String category,
            String status,
            long daysRemaining) {

        this.id = id;
        this.title = title;
        this.category = category;
        this.status = status;
        this.daysRemaining = daysRemaining;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }

    public long getDaysRemaining() {
        return daysRemaining;
    }
}