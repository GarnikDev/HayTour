package com.sevitours.demo.tour_price.controller;

import com.sevitours.demo.tour_price.service.TourPriceService;
import com.sevitours.demo.tour_price.model.TourPrice;
import com.sevitours.demo.tour_price.model.TourPriceDto;
import com.sevitours.demo.tour_price.service.UpdateTourPriceCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tour_price")
public class TourPriceController {

    private TourPriceService tourPriceService;

    public TourPriceController(TourPriceService tourPriceService) {
        this.tourPriceService = tourPriceService;
    }

    @PostMapping("/tour_price")
    public ResponseEntity<TourPriceDto> create(@RequestBody TourPriceDto tourPriceDto) {
        return tourPriceService.create(tourPriceDto);
    }

    @GetMapping("/view")
    public ResponseEntity<List<TourPriceDto>> getAllTourPrices() {
        return tourPriceService.getAll();
    }

    @GetMapping("/view/id/{id}")
    public ResponseEntity<TourPriceDto> getTourPriceById(@PathVariable UUID id) {
        return tourPriceService.getById(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TourPriceDto> updateTourPrice(@PathVariable UUID id, @RequestBody TourPrice tourPrice) {
        return tourPriceService.update(new UpdateTourPriceCommand(id, tourPrice));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTourPrice(@PathVariable UUID id) {
        return tourPriceService.delete(id);
    }
}
