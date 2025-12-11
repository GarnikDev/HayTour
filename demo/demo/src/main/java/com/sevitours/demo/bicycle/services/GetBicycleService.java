package com.sevitours.demo.bicycle.services;

import com.sevitours.demo.Query;
import com.sevitours.demo.bicycle.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ResponseEntity<List<BicycleDto>> execute(Void input) {
        List<BicycleDto> bicyclesDto = bicycleRepository.findAll()
                .stream()
                .map(bicycleMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(bicyclesDto);
    }
}
