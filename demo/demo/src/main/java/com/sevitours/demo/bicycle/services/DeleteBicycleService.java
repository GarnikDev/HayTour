package com.sevitours.demo.bicycle.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.bicycle.Bicycle;
import com.sevitours.demo.bicycle.BicycleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteBicycleService implements Command<Long, Void> {

    private BicycleRepository bicycleRepository;

    public DeleteBicycleService(BicycleRepository bicycleRepository) {
        this.bicycleRepository = bicycleRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Long id) {

        Optional<Bicycle> optionalBicycle = bicycleRepository.findById(id);
        if(optionalBicycle.isPresent()) {
            bicycleRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return null;
    }
}

