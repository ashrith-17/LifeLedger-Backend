package com.lifeledger.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "item_history")
public class ItemHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    private String title;

    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private LifeLedgerItem item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public ItemHistory() {
    }

    public ItemHistory(
            Long id,
            String action,
            LocalDateTime timestamp,
            String title,
            String category,
            LifeLedgerItem item,
            User user) {

        this.id = id;
        this.action = action;
        this.timestamp = timestamp;
        this.title = title;
        this.category = category;
        this.item = item;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
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

    public LifeLedgerItem getItem() {
        return item;
    }

    public void setItem(LifeLedgerItem item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}