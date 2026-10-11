package com.lifeledger.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifeledger.entity.LifeLedgerItem;
import com.lifeledger.entity.User;

public interface LifeLedgerItemRepository
        extends JpaRepository<LifeLedgerItem, Long> {

    List<LifeLedgerItem> findByUser(User user);

    List<LifeLedgerItem> findByUserAndTitleContainingIgnoreCase(
            User user,
            String title
    );

    List<LifeLedgerItem> findByUserAndCategoryIgnoreCase(
            User user,
            String category
    );
}