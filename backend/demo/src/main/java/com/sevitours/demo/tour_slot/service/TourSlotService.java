package com.sevitours.demo.tour_slot.service;

import com.sevitours.demo.common.ItemNotFound;
import com.sevitours.demo.tour_offer.repo.TourOfferRepo;
import com.sevitours.demo.tour_slot.model.TourSlot;
import com.sevitours.demo.tour_slot.model.TourSlotDto;
import com.sevitours.demo.tour_slot.model.TourSlotMapper;
import com.sevitours.demo.tour_slot.repo.TourSlotRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TourSlotService {

    private final TourSlotRepo tourSlotRepo;
    private final TourSlotMapper tourSlotMapper;
    private final TourOfferRepo tourOfferRepo;

    public TourSlotService(TourSlotRepo tourSlotRepo,
                           TourSlotMapper tourSlotMapper,
                           TourOfferRepo tourOfferRepo) {
        this.tourSlotRepo = tourSlotRepo;
        this.tourSlotMapper = tourSlotMapper;
        this.tourOfferRepo = tourOfferRepo;
    }

    public ResponseEntity<TourSlotDto> create(TourSlotDto dto) {
        // 1. Map DTO to Entity
        TourSlot tourSlot = tourSlotMapper.toEntity(dto);

        // 2. Link the Parent (Prevents NPE)
        if (dto.getTourOfferId() != null) {
            var offer = tourOfferRepo.findById(dto.getTourOfferId())
                    .orElseThrow(() -> new ItemNotFound("Tour Offer"));
            tourSlot.setTourOffer(offer);
        }

        TourSlot savedTourSlot = tourSlotRepo.save(tourSlot);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TourSlotDto(savedTourSlot));
    }

    public ResponseEntity<TourSlotDto> update(UpdateTourSlotCommand command) {
        TourSlot existing = tourSlotRepo.findById(command.getId())
                .orElseThrow(() -> new ItemNotFound("Tour slot"));

        // Update fields from the command's entity
        TourSlot source = command.getTourSlot();
        existing.setStartTime(source.getStartTime());
        existing.setEndTime(source.getEndTime());

        // Note: The parent tourOffer link usually stays the same during update

        tourSlotRepo.save(existing);
        return ResponseEntity.status(HttpStatus.OK).body(new TourSlotDto(existing));
    }

    public ResponseEntity<Void> delete(UUID id) {

        Optional<TourSlot> tourSlot = tourSlotRepo.findById(id);
        if(tourSlot.isPresent()) {
            tourSlotRepo.delete(tourSlot.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        throw new ItemNotFound("Tour slot");

    }

    public ResponseEntity<List<TourSlotDto>> getAll() {
        List<TourSlotDto> tourSlotDto = tourSlotRepo.findAll()
                .stream()
                .map(tourSlotMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(tourSlotDto);
    }

    public ResponseEntity<TourSlotDto> getById(UUID id) {
        Optional<TourSlot> tourSlot = tourSlotRepo.findById(id);
        if(tourSlot.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(tourSlotMapper.toDto(tourSlot.get()));
        }else {
            throw new ItemNotFound("Tour slot");
        }
    }
}
