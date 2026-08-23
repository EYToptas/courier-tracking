package com.couriertracking.dto;
import java.time.LocalDateTime;
public record AdminResponse(
        Long id,
        String name,
        LocalDateTime createdAt
) {
}
