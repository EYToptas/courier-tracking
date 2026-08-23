package com.couriertracking.mapper;

import com.couriertracking.dto.CourierResponse;
import com.couriertracking.model.Courier;

public class CourierMapper {
    //bu sınıfların amacı json donerken nesnenin kendisini degilde nesnenin API da gostermek istedigimiz kisimlari cekmek
    //fakat suanda tamamen ayni degiskenleri oldugundan farkli siniflarmis gibi durmuyor
    public static CourierResponse toResponse(Courier courier){
        return new CourierResponse(
                courier.getId(),
                courier.getName(),
                courier.getLastLatitude(),
                courier.getLastLongitude(),
                courier.getLastLocationTime(),
                courier.getTotalDistanceMeters(),
                courier.getCreatedAt(),
                courier.getUpdatedAt()
        );
    }
}
