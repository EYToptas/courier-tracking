package com.couriertracking.dto;

import java.time.LocalDateTime;

public record CourierResponse(
        Long id,
        String name,
        Double lastLatitude,
        Double lastLongitude,
        LocalDateTime lastLocationTime,
        Double totalDistanceMeters,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
