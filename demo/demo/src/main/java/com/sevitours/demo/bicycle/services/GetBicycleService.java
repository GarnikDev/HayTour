package com.sevitours.demo.bicycle.services;

import com.sevitours.demo.bicycle.Bicycle;
import com.sevitours.demo.bicycle.BicycleRepository;
import com.sevitours.demo.bicycle.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetBicycleService implements Query<Void, String> {

    public final BicycleRepository bicycleRepository;

    public GetBicycleService(BicycleRepository bicycleRepository) {
        this.bicycleRepository = bicycleRepository;
    }

    @Override
    public ResponseEntity<List<Bicycle>> execute(Void input) {
        List<Bicycle>  bicycles = bicycleRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(bicycles);
    }
}
