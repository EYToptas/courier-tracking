package com.couriertracking.kafka;
import java.time.Instant;
public record CourierLocationMessage(
        Long courierId,
        Double latitude,
        Double longitude,
        Instant time
) {

}
