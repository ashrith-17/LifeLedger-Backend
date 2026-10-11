package com.lifeledger.dto;

import java.time.LocalDate;

public class ReminderResponse {

    private Long id;
    private String title;
    private String category;
    private LocalDate dueDate;
    private LocalDate reminderDate;
    private long daysRemaining;

    public ReminderResponse(
            Long id,
            String title,
            String category,
            LocalDate dueDate,
            LocalDate reminderDate,
            long daysRemaining) {

        this.id = id;
        this.title = title;
        this.category = category;
        this.dueDate = dueDate;
        this.reminderDate = reminderDate;
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReminderDate() {
        return reminderDate;
    }

    public long getDaysRemaining() {
        return daysRemaining;
    }
}