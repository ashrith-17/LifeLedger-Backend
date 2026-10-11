package com.lifeledger.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifeledger.entity.ItemHistory;
import com.lifeledger.entity.User;

public interface ItemHistoryRepository
        extends JpaRepository<ItemHistory, Long> {

    List<ItemHistory> findByUserOrderByTimestampDesc(User user);
}