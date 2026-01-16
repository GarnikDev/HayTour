package com.sevitours.demo.bicycle.services;

import com.sevitours.demo.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sevitours.demo.bicycle.*;

import java.util.Optional;

@Service
public class GetBicycleByIdService implements Query<Integer, BicycleDto> {

    private final BicycleRepository bicycleRepository;

    public GetBicycleByIdService(BicycleRepository bicycleRepository) {
        this.bicycleRepository = bicycleRepository;
    }

    @Override
    public ResponseEntity<BicycleDto> execute(Integer input){
        Optional<Bicycle> bicycleOptional = bicycleRepository.findById(input);
        if(bicycleOptional.isPresent()){
            return ResponseEntity.ok(new BicycleDto(bicycleOptional.get()));
        }
        return null;
    }
}
