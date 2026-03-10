package com.sevitours.demo.tour_slot.controller;

import com.sevitours.demo.tour_slot.model.TourSlot;
import com.sevitours.demo.tour_slot.model.TourSlotDto;
import com.sevitours.demo.tour_slot.service.TourSlotService;
import com.sevitours.demo.tour_slot.service.UpdateTourSlotCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tour_slots")
public class TourSlotController {

    private final TourSlotService tourSlotService;

    public TourSlotController(TourSlotService tourSlotService) {
        this.tourSlotService = tourSlotService;
    }

    @PostMapping("/tour_slot")
    public ResponseEntity<TourSlotDto> addTourSlot(@RequestBody TourSlotDto tourSlotDto) {
        return tourSlotService.create(tourSlotDto);
    }

    @GetMapping("/view")
    public ResponseEntity<List<TourSlotDto>> getAllTourSlots() {
        return tourSlotService.getAll();
    }

    @GetMapping("/view/id/{id}")
    public ResponseEntity<TourSlotDto> getTourSlotById(@PathVariable UUID id) {
        return tourSlotService.getById(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TourSlotDto> updateTourSlot(@PathVariable UUID id, @RequestBody TourSlot tourSlot) {
        return tourSlotService.update(new UpdateTourSlotCommand(id, tourSlot));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTourSlot(@PathVariable UUID id) {
        return tourSlotService.delete(id);
    }
}
