package com.couriertracking.dto;
import jakarta.validation.constraints.NotBlank;

public record AdminRequest(
        @NotBlank(message = "Admin name is required")
        String name
) { }
