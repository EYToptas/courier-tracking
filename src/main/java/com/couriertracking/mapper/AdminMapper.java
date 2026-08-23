package com.couriertracking.mapper;

import com.couriertracking.dto.AdminResponse;
import com.couriertracking.model.Admin;

public class AdminMapper {
    //bu sınıfların amacı json donerken nesnenin kendisini degilde nesnenin API da gostermek istedigimiz kisimlari cekmek
    //fakat suanda tamamen ayni degiskenleri oldugundan farkli siniflarmis gibi durmuyor
    public static AdminResponse toResponse(Admin admin){
        return new AdminResponse(
                admin.getId(),
                admin.getName(),
                admin.getCreatedAt()
        );
    }
}
