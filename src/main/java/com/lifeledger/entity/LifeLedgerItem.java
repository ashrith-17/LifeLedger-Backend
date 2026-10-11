package com.lifeledger.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "lifeledger_items")
public class LifeLedgerItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;

    private String description;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate dueDate;

    private Double cost;

    private String recurrence;

    private Boolean reminderEnabled;

    private Integer reminderDaysBefore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public LifeLedgerItem() {
    }

    public LifeLedgerItem(
            Long id,
            String title,
            String category,
            String description,
            LocalDate startDate,
            LocalDate dueDate,
            Double cost,
            String recurrence,
            Boolean reminderEnabled,
            Integer reminderDaysBefore,
            User user) {

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
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(String recurrence) {
        this.recurrence = recurrence;
    }

    public Boolean getReminderEnabled() {
        return reminderEnabled;
    }

    public void setReminderEnabled(Boolean reminderEnabled) {
        this.reminderEnabled = reminderEnabled;
    }

    public Integer getReminderDaysBefore() {
        return reminderDaysBefore;
    }

    public void setReminderDaysBefore(Integer reminderDaysBefore) {
        this.reminderDaysBefore = reminderDaysBefore;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}