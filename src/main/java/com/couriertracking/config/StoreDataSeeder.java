package com.couriertracking.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.couriertracking.model.Store;
import com.couriertracking.repository.StoreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class StoreDataSeeder implements CommandLineRunner {

    private StoreRepository storeRepository;
    private ObjectMapper objectMapper= new ObjectMapper();

    public StoreDataSeeder(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        if(storeRepository.count()>0){
            return;// count>0 olduğundan bu metot önceden çalıştırılmış demek ondan dolayı öyleyse hiç bişi yapmasın diye return
        }
        ClassPathResource resource = new ClassPathResource("data/stores.json");
        List<StoreJson> stores = objectMapper.readValue(
                resource.getInputStream(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, StoreJson.class)
        );


        stores.forEach( s -> storeRepository.save(
                Store.builder()
                        .name(s.name())
                        .latitude(s.latitude())
                        .longitude(s.longitude())
                        .build()
        ));
    }




    private record StoreJson(String name, Double latitude, Double longitude) { }//amacı sadece veri tasimak ondan record

}
