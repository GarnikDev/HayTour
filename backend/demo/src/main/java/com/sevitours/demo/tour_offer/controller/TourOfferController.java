package com.sevitours.demo.tour_offer.controller;

import com.sevitours.demo.tour_offer.model.TourOfferDto;
import com.sevitours.demo.tour_offer.services.TourOfferService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tourOffers")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS})
public class TourOfferController {

    private final TourOfferService tourOfferService;

    public TourOfferController(TourOfferService tourOfferService) {
        this.tourOfferService = tourOfferService;
    }

    @PostMapping(value = "/tourOffer", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<TourOfferDto> create(
            @RequestPart("tourOffer") TourOfferDto tourOfferDto, // Change this to DTO
            @RequestPart("image") MultipartFile image
    ) {
        return tourOfferService.create(tourOfferDto, image);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<TourOfferDto> get(@PathVariable UUID id) {
        return tourOfferService.findById(id);
    }

    @GetMapping("/view")
    public ResponseEntity<List<TourOfferDto>> getAll() {
        return tourOfferService.findAll();
    }

    @PutMapping(value = "/edit/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<TourOfferDto> update(
            @PathVariable UUID id,
            @RequestPart("tourOffer") TourOfferDto tourOfferDto,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        return tourOfferService.update(id, tourOfferDto, image);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return tourOfferService.delete(id);
    }

}
