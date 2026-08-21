package com.couriertracking.repository;
import com.couriertracking.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CourierRepository extends JpaRepository<Courier, Long>{
}
