package com.lifeledger.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lifeledger.dto.HistoryResponse;
import com.lifeledger.entity.ItemHistory;
import com.lifeledger.entity.User;
import com.lifeledger.respository.ItemHistoryRepository;
import com.lifeledger.respository.UserRepository;

@Service
public class HistoryService {

    private final ItemHistoryRepository historyRepository;
    private final UserRepository userRepository;

    public HistoryService(
            ItemHistoryRepository historyRepository,
            UserRepository userRepository) {

        this.historyRepository = historyRepository;
        this.userRepository = userRepository;
    }

    public void recordHistory(
            String action,
            Long itemId,
            String title,
            String category,
            com.lifeledger.entity.LifeLedgerItem item,
            User user) {

        ItemHistory history = new ItemHistory();

        history.setAction(action);
        history.setTimestamp(LocalDateTime.now());
        history.setItem(item);
        history.setUser(user);
        history.setTitle(title);
        history.setCategory(category);

        historyRepository.save(history);
    }

    public List<HistoryResponse> getHistory(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return historyRepository
                .findByUserOrderByTimestampDesc(user)
                .stream()
                .map(history ->
                        new HistoryResponse(
                                history.getId(),
                                history.getItem().getId(),
                                history.getTitle(),
                                history.getCategory(),
                                history.getAction(),
                                history.getTimestamp()))
                .toList();
    }
}