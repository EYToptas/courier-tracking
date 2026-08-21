package com.couriertracking.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name="store_entry_events")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class StoreEntryEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY,optional = false)
    @JoinColumn(name = "courier_id",nullable = false)
    private Courier courier;

    @ManyToOne(fetch= FetchType.LAZY,optional = false)
    @JoinColumn(name = "store_id",nullable = false)
    private Store store;

    @Column(nullable = false)
    private LocalDateTime entryTime;
}
