package com.couriertracking.model;

import io.lettuce.core.DriverInfo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="stores")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;


}
