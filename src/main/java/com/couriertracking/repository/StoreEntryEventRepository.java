package com.couriertracking.repository;

import com.couriertracking.model.StoreEntryEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StoreEntryEventRepository extends JpaRepository<StoreEntryEvent, Long> {
    List<StoreEntryEvent> findByCourierId(Long courierId);
}
