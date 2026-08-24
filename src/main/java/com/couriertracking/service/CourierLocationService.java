package com.couriertracking.service;

import com.couriertracking.kafka.CourierLocationMessage;
import com.couriertracking.model.Courier;
import com.couriertracking.model.Store;
import com.couriertracking.model.StoreEntryEvent;
import com.couriertracking.repository.CourierRepository;
import com.couriertracking.repository.StoreEntryEventRepository;
import com.couriertracking.util.HaversineCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Service
@RequiredArgsConstructor
@Slf4j
public class CourierLocationService {
    private static final double STORE_RADIUS_METERS = 100.0;
    private static final Duration DUPLICATE_ENTRY_WINDOW = Duration.ofMinutes(1);
    private final CourierRepository courierRepository;
    private final StoreEntryEventRepository storeEntryEventRepository;
    private final StoreService storeService;
    private final StringRedisTemplate redisTemplate;

    public void processLocation(CourierLocationMessage message) {
        Courier courier = courierRepository.findById(message.courierId()).orElse(null);
        if (courier == null) {
            log.warn("Bilinmeyen courierId={} için konum geldi, yok sayılıyor", message.courierId());
            return;
        }
        //kurye totalDistanceGuncelleme
        if (courier.getLastLatitude() != null && courier.getLastLongitude() != null) {
            double delta = HaversineCalculator.distanceInMeters(
                    courier.getLastLatitude(), courier.getLastLongitude(),
                    message.latitude(), message.longitude());
            courier.setTotalDistanceMeters(courier.getTotalDistanceMeters() + delta);
        }
        courier.setLastLatitude(message.latitude());
        courier.setLastLongitude(message.longitude());
        courier.setLastLocationTime(LocalDateTime.ofInstant(message.time(), ZoneId.systemDefault()));
        courierRepository.save(courier);


        for (Store store : storeService.getAllStores()) {
            double distance = HaversineCalculator.distanceInMeters(
                    message.latitude(), message.longitude(),
                    store.getLatitude(), store.getLongitude());

            if (distance <= STORE_RADIUS_METERS) {
                handlePossibleEntry(courier, store, message, distance);
            }
        }
    }
    private void handlePossibleEntry(Courier courier, Store store, CourierLocationMessage message, double distance) {
        String redisKey = "entry:" + courier.getId() + ":" + store.getId();

        Boolean isNewEntry = redisTemplate.opsForValue()
                .setIfAbsent(redisKey, "1", DUPLICATE_ENTRY_WINDOW);

        if (Boolean.TRUE.equals(isNewEntry)) {
            StoreEntryEvent event = StoreEntryEvent.builder()
                    .courier(courier)
                    .store(store)
                    .entryTime(LocalDateTime.ofInstant(message.time(), ZoneId.systemDefault()))
                    .build();
            storeEntryEventRepository.save(event);
            log.info("Kurye {} -> {} mağazasına girdi ({}m)", courier.getId(), store.getName(), Math.round(distance));
        } else {
            log.debug("Kurye {} için {} mağazasına tekrar giriş yok sayıldı (1 dk içinde)", courier.getId(), store.getName());
        }
    }

}
