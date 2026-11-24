package com.couriertracking.repository;

import com.couriertracking.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CourierRepository extends JpaRepository<Courier, Long>{

    @Modifying
    @Transactional
    @Query("DELETE FROM Courier c WHERE c.id = :id")
    void deleteByXId(Long id);

}
