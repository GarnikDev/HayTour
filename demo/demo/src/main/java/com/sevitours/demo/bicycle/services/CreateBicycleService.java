package com.sevitours.demo.bicycle.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.bicycle.Bicycle;
import com.sevitours.demo.bicycle.BicycleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateBicycleService implements Command<Bicycle, String> {

    private final BicycleRepository bicycleRepository;

    public CreateBicycleService(BicycleRepository bicycleRepository) {
        this.bicycleRepository = bicycleRepository;
    }

    @Override
    public ResponseEntity<String> execute(Bicycle bicycle) {
        bicycleRepository.save(bicycle);
        return ResponseEntity.status(HttpStatus.CREATED).body("Bicycle Created");
    }
}
