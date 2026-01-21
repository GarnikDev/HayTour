package com.sevitours.demo.bicycle.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.bicycle.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateBicycleService implements Command<UpdateBicycleCommand, BicycleDto> {

    private BicycleRepository bicycleRepository;


    public UpdateBicycleService(BicycleRepository bicycleRepository) {
        this.bicycleRepository = bicycleRepository;
    }

    @Override
    public ResponseEntity<BicycleDto> execute(UpdateBicycleCommand command) {
        Optional<Bicycle> optionalBicycle = bicycleRepository.findById(command.getId());
        if (optionalBicycle.isPresent()) {
            Bicycle bicycle = command.getBicycle();
            bicycle.setId(command.getId());
            bicycleRepository.save(bicycle);
            return ResponseEntity.ok(new BicycleDto(bicycle));
        }

        return null;
    }
}
