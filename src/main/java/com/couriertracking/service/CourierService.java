package com.couriertracking.service;

import com.couriertracking.dto.CourierDistanceResponse;
import com.couriertracking.dto.CourierRequest;
import com.couriertracking.dto.CourierResponse;
import com.couriertracking.exception.ResourceNotFoundException;
import com.couriertracking.mapper.CourierMapper;
import com.couriertracking.model.Courier;
import com.couriertracking.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourierService {
    private final CourierRepository courierRepository;
    public CourierResponse create(CourierRequest request){
        Courier courier = Courier.builder()
                .name(request.name())
                .totalDistanceMeters(0.0)
                .build();
        return CourierMapper.toResponse(courierRepository.save(courier));
    }

    public List<CourierResponse> getAll(){
        return courierRepository.findAll().stream().map(courier -> CourierMapper.toResponse(courier)).toList();
    }

    public CourierResponse getByID(Long id){
        return CourierMapper.toResponse(searchId(id));

    }

    public CourierDistanceResponse getTotalDistance(Long id) {
        Courier courier = searchId(id);
        return new CourierDistanceResponse(courier.getId(), courier.getTotalDistanceMeters());
    }

    public CourierResponse update(Long id, CourierRequest request){
        Courier courier = searchId(id);
        courier.setName(request.name());
        return CourierMapper.toResponse(courierRepository.save(courier));
    }
    public void delete(Long id) {
        courierRepository.delete(searchId(id));
    }

    private Courier searchId(Long id) {
        return courierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Courier not found with id: " + id));
    }

}
