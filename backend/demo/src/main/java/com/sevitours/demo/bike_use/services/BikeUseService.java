package com.sevitours.demo.bike_use.services;


import com.sevitours.demo.bike_use.model.BikeUse;
import com.sevitours.demo.bike_use.model.BikeUseDto;
import com.sevitours.demo.bike_use.model.BikeUseMapper;
import com.sevitours.demo.bike_use.repo.BikeUseRepository;
import com.sevitours.demo.common.ItemNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BikeUseService {

    private final BikeUseRepository bikeUseRepository;
    private final BikeUseMapper bikeUseMapper;

    public BikeUseService(BikeUseRepository bikeUseRepository,
                          BikeUseMapper bikeUseMapper) {
        this.bikeUseRepository = bikeUseRepository;
        this.bikeUseMapper = bikeUseMapper;
    }

    public ResponseEntity<BikeUseDto> create(BikeUse bikeUse) {
        BikeUse savedBikeUse = bikeUseRepository.save(bikeUse);

        return ResponseEntity.status(HttpStatus.CREATED).body(new  BikeUseDto(savedBikeUse));
    }

    public ResponseEntity<Void> delete(UUID id) {

        Optional<BikeUse> optionalBikeUse = bikeUseRepository.findById(id);
        if(optionalBikeUse.isPresent()) {
            bikeUseRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        throw new ItemNotFound("Bike Use");
    }

    public ResponseEntity<List<BikeUseDto>> getAll() { // Get all bikeUses
        List<BikeUseDto> bikeUsesDto = bikeUseRepository.findAll()
                .stream()
                .map(bikeUseMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(bikeUsesDto);
    }

    public ResponseEntity<BikeUseDto> getById(UUID id){ // Get bikeUse by ID
        Optional<BikeUse> bikeUseOptional = bikeUseRepository.findById(id);
        if (bikeUseOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(bikeUseMapper.toDto(bikeUseOptional.get()));
        }else  {
            throw new ItemNotFound("Bike Use");
        }
    }

    public ResponseEntity<BikeUseDto> update(UpdateBikeUseCommand command) {
        Optional<BikeUse> optionalBikeUse = bikeUseRepository.findById(command.getId());
        if (optionalBikeUse.isPresent()) {
            BikeUse bikeUse = command.getBikeUse();
            bikeUse.setId(command.getId());
            bikeUseRepository.save(bikeUse);
            return ResponseEntity.status(HttpStatus.OK).body(bikeUseMapper.toDto(bikeUse));
        }

        throw new ItemNotFound("Bike Use");
    }

}
