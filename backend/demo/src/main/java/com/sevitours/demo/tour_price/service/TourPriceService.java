package com.sevitours.demo.tour_price.service;

import com.sevitours.demo.common.ItemNotFound;
import com.sevitours.demo.tour_offer.repo.TourOfferRepo;
import com.sevitours.demo.tour_price.model.TourPriceMapper;
import com.sevitours.demo.tour_price.repo.TourPriceRepo;
import com.sevitours.demo.tour_price.model.TourPrice;
import com.sevitours.demo.tour_price.model.TourPriceDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TourPriceService {

    private final TourPriceRepo tourPriceRepo;
    private final TourPriceMapper tourPriceMapper;
    private final TourOfferRepo tourOfferRepo;

    public TourPriceService(TourPriceRepo tourPriceRepo,
                            TourPriceMapper tourPriceMapper,
                            TourOfferRepo tourOfferRepo) {
        this.tourPriceRepo = tourPriceRepo;
        this.tourPriceMapper = tourPriceMapper;
        this.tourOfferRepo = tourOfferRepo;
    }

    public ResponseEntity<TourPriceDto> create(TourPriceDto dto) {
        // 1. Map DTO to Entity
        TourPrice tourPrice = tourPriceMapper.toEntity(dto);

        // 2. FIND the TourOffer and link it (This prevents the NPE)
        if (dto.getTourOfferId() != null) {
            var offer = tourOfferRepo.findById(dto.getTourOfferId())
                    .orElseThrow(() -> new ItemNotFound("Tour Offer"));
            tourPrice.setTourOffer(offer);
        } else {
            throw new IllegalArgumentException("Tour Offer ID is required");
        }

        // 3. Save
        TourPrice savedTourPrice = tourPriceRepo.save(tourPrice);

        // 4. Return DTO (The constructor will now find the TourOffer and work!)
        return ResponseEntity.status(HttpStatus.CREATED).body(new TourPriceDto(savedTourPrice));
    }

    // Update the update method similarly to ensure the link isn't lost
    public ResponseEntity<TourPriceDto> update(UpdateTourPriceCommand command) {
        TourPrice existing = tourPriceRepo.findById(command.getId())
                .orElseThrow(() -> new ItemNotFound("Tour price"));

        TourPrice source = command.getTourPrice();

        existing.setCategory(source.getCategory());
        existing.setPrice(source.getPrice());
        existing.setIsForeigner(source.getIsForeigner());

        // Save the updated existing entity (which already has the tourOffer link)
        tourPriceRepo.save(existing);
        return ResponseEntity.ok(new TourPriceDto(existing));
    }

    public ResponseEntity<Void> delete(UUID id) {

        Optional<TourPrice> tourPrice = tourPriceRepo.findById(id);
        if(tourPrice.isPresent()) {
            tourPriceRepo.delete(tourPrice.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        throw new ItemNotFound("Tour price");

    }

    public ResponseEntity<List<TourPriceDto>> getAll() {
        List<TourPriceDto> tourPriceDto = tourPriceRepo.findAll()
                .stream()
                .map(tourPriceMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(tourPriceDto);
    }

    public ResponseEntity<TourPriceDto> getById(UUID id) {
        Optional<TourPrice> tourPrice = tourPriceRepo.findById(id);
        if(tourPrice.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(tourPriceMapper.toDto(tourPrice.get()));
        }else {
            throw new ItemNotFound("Tour price");
        }
    }

}
