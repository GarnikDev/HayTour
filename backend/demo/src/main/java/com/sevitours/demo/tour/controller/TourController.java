package com.sevitours.demo.tour.controller;

import com.sevitours.demo.tour.model.Tour;
import com.sevitours.demo.tour.model.TourDto;
import com.sevitours.demo.tour.services.UpdateTourCommand;
import com.sevitours.demo.tour.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tours")
public class TourController {

    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @PostMapping("/tour")
    public ResponseEntity<TourDto> create(@RequestBody Tour tour) {
        return tourService.create(tour);
    }

    @GetMapping("/view")
    public ResponseEntity<List<TourDto>> getAll() {
        return tourService.getAll();
    }

    @GetMapping("/view/id/{id}")
    public ResponseEntity<TourDto> getById(@PathVariable UUID id) {
        return tourService.getById(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TourDto> update(@PathVariable UUID id, @RequestBody Tour tour) {
        return tourService.update(new UpdateTourCommand(id, tour));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return tourService.delete(id);
    }
}
