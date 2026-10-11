package com.lifeledger.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lifeledger.dto.ReminderResponse;
import com.lifeledger.entity.LifeLedgerItem;
import com.lifeledger.entity.User;
import com.lifeledger.respository.LifeLedgerItemRepository;
import com.lifeledger.respository.UserRepository;

@Service
public class ReminderService {

    private final LifeLedgerItemRepository itemRepository;
    private final UserRepository userRepository;

    public ReminderService(
            LifeLedgerItemRepository itemRepository,
            UserRepository userRepository) {

        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public List<ReminderResponse> getReminders(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        List<LifeLedgerItem> items =
                itemRepository.findByUser(user);

        LocalDate today = LocalDate.now();

        return items.stream()
                .filter(item ->
                        Boolean.TRUE.equals(
                                item.getReminderEnabled()))
                .map(item -> {

                    int reminderDays =
                            item.getReminderDaysBefore() != null
                                    ? item.getReminderDaysBefore()
                                    : 0;

                    LocalDate reminderDate =
                            item.getDueDate()
                                    .minusDays(reminderDays);

                    long daysRemaining =
                            ChronoUnit.DAYS.between(
                                    today,
                                    item.getDueDate());

                    return new ReminderResponse(
                            item.getId(),
                            item.getTitle(),
                            item.getCategory(),
                            item.getDueDate(),
                            reminderDate,
                            daysRemaining);
                })
                .filter(reminder ->
                        !reminder.getDueDate().isBefore(today))
                .toList();
    }
}