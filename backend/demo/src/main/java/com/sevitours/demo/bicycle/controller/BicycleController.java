package com.sevitours.demo.bicycle.controller;

import com.sevitours.demo.bicycle.model.Bicycle;
import com.sevitours.demo.bicycle.model.BicycleDto;
import com.sevitours.demo.bicycle.service.UpdateBicycleCommand;
import com.sevitours.demo.bicycle.service.*;
import com.sevitours.demo.bicycle.enums.BikeType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/bicycles")
public class BicycleController {

    private final BicycleService bicycleService;

    public BicycleController(BicycleService bicycleService) {
        this.bicycleService = bicycleService;
    }

    @PostMapping("/bicycle")
    public ResponseEntity<BicycleDto> createBicycle(@RequestBody Bicycle bicycle) {
        return bicycleService.create(bicycle);
    }

    @GetMapping("/view")
    public ResponseEntity<List<BicycleDto>> getAllBicycles() {
        Void input = null;
        return bicycleService.getAll(input);
    }

    @GetMapping("/view/id/{id}")
    public ResponseEntity<BicycleDto> getBicycleById(@PathVariable UUID id) {
        return bicycleService.getById(id);
    }

    @GetMapping("/view/type/{type}")
    public ResponseEntity<List<BicycleDto>> getAllBicyclesByType(@PathVariable BikeType type) {
        return bicycleService.getByType(type);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<BicycleDto> updateBicycle(@PathVariable UUID id, @RequestBody Bicycle bicycle) {
        return bicycleService.update(new UpdateBicycleCommand(bicycle, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBicycle(@PathVariable UUID id) {
        return bicycleService.delete(id);
    }

}
