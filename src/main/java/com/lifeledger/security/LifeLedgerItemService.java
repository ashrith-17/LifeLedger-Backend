package com.lifeledger.security;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lifeledger.dto.ItemStatusResponse;
import com.lifeledger.dto.LifeLedgerItemRequest;
import com.lifeledger.dto.LifeLedgerItemResponse;
import com.lifeledger.entity.ItemHistory;
import com.lifeledger.entity.LifeLedgerItem;
import com.lifeledger.entity.User;
import com.lifeledger.respository.ItemHistoryRepository;
import com.lifeledger.respository.LifeLedgerItemRepository;
import com.lifeledger.respository.UserRepository;

@Service
public class LifeLedgerItemService {

    private final LifeLedgerItemRepository itemRepository;
    private final UserRepository userRepository;
    private final ItemHistoryRepository historyRepository;

    public LifeLedgerItemService(
            LifeLedgerItemRepository itemRepository,
            UserRepository userRepository,
            ItemHistoryRepository historyRepository) {

        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.historyRepository = historyRepository;
    }

    // CREATE
    public LifeLedgerItemResponse createItem(
            LifeLedgerItemRequest request,
            String email) {

        User user = getUser(email);

        LifeLedgerItem item = new LifeLedgerItem();

        item.setTitle(request.getTitle());
        item.setCategory(request.getCategory());
        item.setDescription(request.getDescription());
        item.setStartDate(request.getStartDate());
        item.setDueDate(request.getDueDate());
        item.setCost(request.getCost());
        item.setRecurrence(request.getRecurrence());
        item.setReminderEnabled(request.getReminderEnabled());
        item.setReminderDaysBefore(request.getReminderDaysBefore());
        item.setUser(user);

        LifeLedgerItem savedItem =
                itemRepository.save(item);

        // Record CREATE history
        recordHistory(
                "CREATED",
                savedItem,
                user);

        return convertToResponse(savedItem);
    }

    // GET ALL ITEMS
    public List<LifeLedgerItemResponse> getMyItems(
            String email) {

        User user = getUser(email);

        return itemRepository.findByUser(user)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // GET SINGLE ITEM
    public LifeLedgerItemResponse getItem(
            Long id,
            String email) {

        User user = getUser(email);

        LifeLedgerItem item =
                itemRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Item not found"));

        checkOwnership(item, user);

        return convertToResponse(item);
    }

    // UPDATE
    public LifeLedgerItemResponse updateItem(
            Long id,
            LifeLedgerItemRequest request,
            String email) {

        User user = getUser(email);

        LifeLedgerItem item =
                itemRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Item not found"));

        checkOwnership(item, user);

        item.setTitle(request.getTitle());
        item.setCategory(request.getCategory());
        item.setDescription(request.getDescription());
        item.setStartDate(request.getStartDate());
        item.setDueDate(request.getDueDate());
        item.setCost(request.getCost());
        item.setRecurrence(request.getRecurrence());
        item.setReminderEnabled(
                request.getReminderEnabled());
        item.setReminderDaysBefore(
                request.getReminderDaysBefore());

        LifeLedgerItem updatedItem =
                itemRepository.save(item);

        // Record UPDATE history
        recordHistory(
                "UPDATED",
                updatedItem,
                user);

        return convertToResponse(updatedItem);
    }

    // DELETE
    public void deleteItem(
            Long id,
            String email) {

        User user = getUser(email);

        LifeLedgerItem item =
                itemRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Item not found"));

        checkOwnership(item, user);

        // Save history BEFORE deleting item
        recordHistory(
                "DELETED",
                item,
                user);

        itemRepository.delete(item);
    }

    // STATUS
    public List<ItemStatusResponse> getItemStatuses(
            String email) {

        User user = getUser(email);

        List<LifeLedgerItem> items =
                itemRepository.findByUser(user);

        LocalDate today = LocalDate.now();

        return items.stream()
                .map(item -> {

                    long daysRemaining =
                            ChronoUnit.DAYS.between(
                                    today,
                                    item.getDueDate());

                    String status;

                    if (daysRemaining < 0) {
                        status = "OVERDUE";
                    } else if (daysRemaining <= 30) {
                        status = "DUE_SOON";
                    } else {
                        status = "UPCOMING";
                    }

                    return new ItemStatusResponse(
                            item.getId(),
                            item.getTitle(),
                            item.getCategory(),
                            status,
                            daysRemaining);
                })
                .toList();
    }

    // SEARCH
    public List<LifeLedgerItemResponse> searchItems(
            String email,
            String keyword) {

        User user = getUser(email);

        return itemRepository
                .findByUserAndTitleContainingIgnoreCase(
                        user,
                        keyword)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // CATEGORY FILTER
    public List<LifeLedgerItemResponse> filterByCategory(
            String email,
            String category) {

        User user = getUser(email);

        return itemRepository
                .findByUserAndCategoryIgnoreCase(
                        user,
                        category)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // GET USER
    private User getUser(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"));
    }

    // CHECK OWNERSHIP
    private void checkOwnership(
            LifeLedgerItem item,
            User user) {

        if (!item.getUser()
                .getId()
                .equals(user.getId())) {

            throw new RuntimeException(
                    "You are not allowed to access this item");
        }
    }

    // RECORD HISTORY
    private void recordHistory(
            String action,
            LifeLedgerItem item,
            User user) {

        ItemHistory history = new ItemHistory();

        history.setAction(action);
        history.setTimestamp(
                java.time.LocalDateTime.now());

        history.setTitle(item.getTitle());
        history.setCategory(item.getCategory());

        history.setItem(item);
        history.setUser(user);

        historyRepository.save(history);
    }

    // ENTITY → RESPONSE DTO
    private LifeLedgerItemResponse convertToResponse(
            LifeLedgerItem item) {

        return new LifeLedgerItemResponse(
                item.getId(),
                item.getTitle(),
                item.getCategory(),
                item.getDescription(),
                item.getStartDate(),
                item.getDueDate(),
                item.getCost(),
                item.getRecurrence(),
                item.getReminderEnabled(),
                item.getReminderDaysBefore());
    }
}