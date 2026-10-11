package com.lifeledger.security;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lifeledger.dto.DashboardResponse;
import com.lifeledger.entity.LifeLedgerItem;
import com.lifeledger.entity.User;
import com.lifeledger.respository.LifeLedgerItemRepository;
import com.lifeledger.respository.UserRepository;

@Service
public class DashboardService {

    private final LifeLedgerItemRepository itemRepository;
    private final UserRepository userRepository;

    public DashboardService(
            LifeLedgerItemRepository itemRepository,
            UserRepository userRepository) {

        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public DashboardResponse getDashboard(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        List<LifeLedgerItem> items =
                itemRepository.findByUser(user);

        LocalDate today = LocalDate.now();
        LocalDate dueSoonDate = today.plusDays(30);

        long totalItems = items.size();

        long overdueItems = items.stream()
                .filter(item ->
                        item.getDueDate().isBefore(today))
                .count();

        long dueSoonItems = items.stream()
                .filter(item ->
                        !item.getDueDate().isBefore(today)
                        && !item.getDueDate().isAfter(dueSoonDate))
                .count();

        long upcomingItems = items.stream()
                .filter(item ->
                        item.getDueDate().isAfter(dueSoonDate))
                .count();

        double totalCost = items.stream()
                .filter(item -> item.getCost() != null)
                .mapToDouble(LifeLedgerItem::getCost)
                .sum();

        return new DashboardResponse(
                totalItems,
                upcomingItems,
                overdueItems,
                dueSoonItems,
                totalCost
        );
    }
}