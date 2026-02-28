package com.sevitours.demo.bicycle.service;

import com.sevitours.demo.bicycle.enums.BikeType;
import com.sevitours.demo.bicycle.model.Bicycle;
import com.sevitours.demo.bicycle.model.BicycleDto;
import com.sevitours.demo.bicycle.model.BicycleMapper;
import com.sevitours.demo.bicycle.repo.BicycleRepository;
import com.sevitours.demo.common.ItemNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BicycleService {

    private final BicycleRepository bicycleRepository;
    private final BicycleMapper bicycleMapper;

    public BicycleService(BicycleRepository bicycleRepository,
                          BicycleMapper bicycleMapper) {
        this.bicycleRepository = bicycleRepository;
        this.bicycleMapper = bicycleMapper;
    }

    public ResponseEntity<BicycleDto> create(Bicycle bicycle) {
        Bicycle savedBicycle = bicycleRepository.save(bicycle);

        return ResponseEntity.status(HttpStatus.CREATED).body(new  BicycleDto(savedBicycle));
    }

    public ResponseEntity<Void> delete(UUID id) {

        Optional<Bicycle> optionalBicycle = bicycleRepository.findById(id);
        if(optionalBicycle.isPresent()) {
            bicycleRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        throw new ItemNotFound("Bicycle");
    }

    public ResponseEntity<List<BicycleDto>> getAll(Void input) { // Get all bicycles
        List<BicycleDto> bicyclesDto = bicycleRepository.findAll()
                .stream()
                .map(bicycleMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(bicyclesDto);
    }

    public ResponseEntity<BicycleDto> getById(UUID id){ // Get bicycle by ID
        Optional<Bicycle> bicycleOptional = bicycleRepository.findById(id);
        if (bicycleOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(bicycleMapper.toDto(bicycleOptional.get()));
        }else  {
            throw new ItemNotFound("Bicycle");
        }
    }

    public ResponseEntity<List<BicycleDto>> getByType(BikeType type){ //get bicycles by type
        List<Bicycle> bicycleWithType = bicycleRepository.findAllByType(type);

        if (!bicycleWithType.isEmpty()) {
            List<BicycleDto> bicycleDtos = bicycleWithType.stream()
                    .map(bicycleMapper::toDto)
                    .toList();
            return ResponseEntity.ok(bicycleDtos);
        }
        throw new ItemNotFound("Bicycle");
    }

    public ResponseEntity<BicycleDto> update(UpdateBicycleCommand command) {
        Optional<Bicycle> optionalBicycle = bicycleRepository.findById(command.getId());
        if (optionalBicycle.isPresent()) {
            Bicycle bicycle = command.getBicycle();
            bicycle.setId(command.getId());
            bicycleRepository.save(bicycle);
            return ResponseEntity.ok(new BicycleDto(bicycle));
        }

        throw new ItemNotFound("Bicycle");
    }

}
