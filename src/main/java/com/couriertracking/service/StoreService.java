package com.couriertracking.service;
import com.couriertracking.model.Store;
import com.couriertracking.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    @Cacheable("stores")
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }


}
