package com.sevitours.demo.bicycle.services;

import com.sevitours.demo.Query;
import com.sevitours.demo.bicycle.*;
import com.sevitours.demo.common.enums.BikeType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetBicycleService implements Query<Void, List<BicycleDto>> {

    private final BicycleRepository bicycleRepository;
    private final BicycleMapper bicycleMapper;

    public GetBicycleService(BicycleRepository bicycleRepository,
                             BicycleMapper bicycleMapper) {
        this.bicycleRepository = bicycleRepository;
        this.bicycleMapper = bicycleMapper;
    }

    @Override
    public ResponseEntity<List<BicycleDto>> execute(Void input) { // Get all bicycles
        List<BicycleDto> bicyclesDto = bicycleRepository.findAll()
                .stream()
                .map(bicycleMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(bicyclesDto);
    }

    public ResponseEntity<BicycleDto> execute(Long input){ // Get bicycle by ID
        Optional<Bicycle> bicycleOptional = bicycleRepository.findById(input);
        if (bicycleOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(bicycleMapper.toDto(bicycleOptional.get()));
        }else  {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ResponseEntity<List<BicycleDto>> execute(BikeType type){ //get bicycles by type
        List<Bicycle> bicycleWithType = bicycleRepository.findAllByType(type);

        if (!bicycleWithType.isEmpty()) {
            List<BicycleDto> bicycleDtos = bicycleWithType.stream()
                    .map(bicycleMapper::toDto)
                    .toList();
            return ResponseEntity.ok(bicycleDtos);
        }
        return null;
    }

}
