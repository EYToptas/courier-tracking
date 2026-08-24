package com.couriertracking.dto;

public record CourierDistanceResponse(
        Long courierId,
        Double totalDistanceMeters
){}
