package com.couriertracking.dto;
import jakarta.validation.constraints.NotBlank;

public record CourierRequest(
        @NotBlank(message = "Courier name is required")
        String name
        ) {}
