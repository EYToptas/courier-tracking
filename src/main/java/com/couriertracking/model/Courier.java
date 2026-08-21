package com.couriertracking.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "couriers")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;


    private Double lastLatitude;
    private Double lastLongitude;
    private LocalDateTime lastLocationTime;

    @Builder.Default
    private Double totalDistanceMeters = 0.0;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @PrePersist
    void onCreate(){
        createdAt=LocalDateTime.now();
        updatedAt=LocalDateTime.now();
    }


    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }



}
