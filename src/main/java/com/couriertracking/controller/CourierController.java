package com.couriertracking.controller;


import com.couriertracking.dto.CourierDistanceResponse;
import com.couriertracking.dto.CourierRequest;
import com.couriertracking.dto.CourierResponse;
import com.couriertracking.service.CourierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/couriers")
public class CourierController {
    private final CourierService courierService;

    @PostMapping
    public ResponseEntity<CourierResponse> create(@Valid @RequestBody CourierRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(courierService.create(request));
    }
    @GetMapping
    public ResponseEntity<List<CourierResponse>> getAll() {
        return ResponseEntity.ok(courierService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourierResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(courierService.getByID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourierResponse> update(@PathVariable Long id, @Valid @RequestBody CourierRequest request) {
        return ResponseEntity.ok(courierService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courierService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/distance")
    public ResponseEntity<CourierDistanceResponse> getTotalDistance(@PathVariable Long id) {
        return ResponseEntity.ok(courierService.getTotalDistance(id));
    }

}
