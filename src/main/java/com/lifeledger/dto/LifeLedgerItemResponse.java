package com.lifeledger.dto;

import java.time.LocalDate;

public class LifeLedgerItemResponse {

    private Long id;
    private String title;
    private String category;
    private String description;
    private LocalDate startDate;
    private LocalDate dueDate;
    private Double cost;
    private String recurrence;
    private Boolean reminderEnabled;
    private Integer reminderDaysBefore;

    public LifeLedgerItemResponse(
            Long id,
            String title,
            String category,
            String description,
            LocalDate startDate,
            LocalDate dueDate,
            Double cost,
            String recurrence,
            Boolean reminderEnabled,
            Integer reminderDaysBefore) {

        this.id = id;
        this.title = title;
        this.category = category;
        this.description = description;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.cost = cost;
        this.recurrence = recurrence;
        this.reminderEnabled = reminderEnabled;
        this.reminderDaysBefore = reminderDaysBefore;
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

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Double getCost() {
        return cost;
    }

    public String getRecurrence() {
        return recurrence;
    }

    public Boolean getReminderEnabled() {
        return reminderEnabled;
    }

    public Integer getReminderDaysBefore() {
        return reminderDaysBefore;
    }
}