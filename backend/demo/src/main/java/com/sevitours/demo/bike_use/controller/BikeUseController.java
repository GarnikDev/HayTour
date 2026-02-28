package com.sevitours.demo.bike_use.controller;

import com.sevitours.demo.bike_use.model.BikeUse;
import com.sevitours.demo.bike_use.model.BikeUseDto;
import com.sevitours.demo.bike_use.services.BikeUseService;
import com.sevitours.demo.bike_use.services.UpdateBikeUseCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bike_uses")
public class BikeUseController {

    private final BikeUseService bikeUseService;

    public BikeUseController(BikeUseService bikeUseService) {
        this.bikeUseService = bikeUseService;
    }

    @PostMapping("/bike_use")
    public ResponseEntity<BikeUseDto> createBikeUse(@RequestBody BikeUse bikeUse) {
        return bikeUseService.create(bikeUse);
    }

    @GetMapping("/view")
    public ResponseEntity<List<BikeUseDto>> getAllBikeUse() {
        return bikeUseService.getAll();
    }

    @GetMapping("/view/id/{id}")
    public ResponseEntity<BikeUseDto> getBikeUseById(@PathVariable UUID id) {
        return bikeUseService.getById(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<BikeUseDto> updateBikeUse(@PathVariable UUID id, @RequestBody BikeUse bikeUse) {
        return bikeUseService.update(new UpdateBikeUseCommand(id, bikeUse));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBikeUse(@PathVariable UUID id) {
        return bikeUseService.delete(id);
    }
}
