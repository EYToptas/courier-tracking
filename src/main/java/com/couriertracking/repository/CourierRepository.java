package com.couriertracking.repository;
import com.couriertracking.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourierRepository extends JpaRepository<Courier, Long>{
    @Query("DELETE FROM couriers WHERE couriers.id=:ID" )
    void deleteByXID(Long ID);

}
