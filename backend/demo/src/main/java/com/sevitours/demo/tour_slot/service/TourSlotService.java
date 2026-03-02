package com.sevitours.demo.tour_slot.service;

import com.sevitours.demo.common.ItemNotFound;
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

    public TourSlotService(TourSlotRepo tourSlotRepo,
                           TourSlotMapper tourSlotMapper) {
        this.tourSlotRepo = tourSlotRepo;
        this.tourSlotMapper = tourSlotMapper;
    }

    public ResponseEntity<TourSlotDto> create(TourSlot tourSlot) {
        TourSlot savedTourSlot = tourSlotRepo.save(tourSlot);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TourSlotDto(savedTourSlot));
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

    public ResponseEntity<TourSlotDto> update(UpdateTourSlotCommand command) {
        Optional<TourSlot> optionalTourSlot = tourSlotRepo.findById(command.getId());

        if(optionalTourSlot.isPresent()) {
            TourSlot tourSlot = command.getTourSlot();
            tourSlot.setId(command.getId());
            tourSlotRepo.save(tourSlot);
            return ResponseEntity.status(HttpStatus.OK).body(tourSlotMapper.toDto(tourSlot));
        }

        throw new ItemNotFound("Tour slot");

    }
}
