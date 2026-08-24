package com.couriertracking.kafka;

import com.couriertracking.service.CourierLocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CourierLocationConsumer {

    private final CourierLocationService courierLocationService;

    @KafkaListener(topics = "courier-locations", groupId = "courier-tracking-group")
    public void consume(CourierLocationMessage message) {
        log.info("Kafka mesajı alındı: {}", message);
        courierLocationService.processLocation(message);
    }
}