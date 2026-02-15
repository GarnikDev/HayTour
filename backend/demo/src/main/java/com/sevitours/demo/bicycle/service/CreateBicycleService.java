package com.sevitours.demo.bicycle.service;

import com.sevitours.demo.Command;
import com.sevitours.demo.bicycle.model.Bicycle;
import com.sevitours.demo.bicycle.model.BicycleDto;
import com.sevitours.demo.bicycle.repo.BicycleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateBicycleService implements Command<Bicycle, BicycleDto> {

    private final BicycleRepository bicycleRepository;

    public CreateBicycleService(BicycleRepository bicycleRepository) {
        this.bicycleRepository = bicycleRepository;
    }

    @Override
    public ResponseEntity<BicycleDto> execute(Bicycle bicycle) {
        Bicycle savedBicycle = bicycleRepository.save(bicycle);

        return ResponseEntity.status(HttpStatus.CREATED).body(new  BicycleDto(savedBicycle));
    }
}
