package com.sevitours.demo.stop.services;

import com.sevitours.demo.common.ItemNotFound;
import com.sevitours.demo.stop.model.Stop;
import com.sevitours.demo.stop.model.StopDto;
import com.sevitours.demo.stop.model.StopMapper;
import com.sevitours.demo.stop.repo.StopRepo;
import com.sevitours.demo.tour_offer.model.TourOffer;
import com.sevitours.demo.tour_offer.repo.TourOfferRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StopService {

    private final StopRepo stopRepo;
    private final StopMapper stopMapper;
    private final TourOfferRepo tourOfferRepo;

    public StopService(StopRepo stopRepo,
                       StopMapper stopMapper,
                       TourOfferRepo tourOfferRepo) {
        this.stopRepo = stopRepo;
        this.stopMapper = stopMapper;
        this.tourOfferRepo = tourOfferRepo;
    }

    public ResponseEntity<StopDto> create(StopDto dto) {
        // 1. Map DTO to Entity
        Stop stop = stopMapper.toEntity(dto);

        // 2. Link the TourOffer using the ID from the DTO
        if (dto.getTourOfferId() == null) {
            throw new IllegalArgumentException("TourOffer ID must be provided");
        }

        TourOffer offer = tourOfferRepo.findById(dto.getTourOfferId())
                .orElseThrow(() -> new ItemNotFound("TourOffer"));
        stop.setTourOffer(offer);

        // 3. Metadata defaults
        stop.setCreatedAt(OffsetDateTime.now());
        if (stop.getStopOrder() == null) stop.setStopOrder(0);

        // Ensure coordinates aren't null for the DB
        if (stop.getLatitude() == null) stop.setLatitude(0.0);
        if (stop.getLongitude() == null) stop.setLongitude(0.0);

        Stop savedStop = stopRepo.save(stop);
        return ResponseEntity.status(HttpStatus.CREATED).body(new StopDto(savedStop));
    }

    public ResponseEntity<StopDto> update(UpdateStopCommand command) {
        Stop existing = stopRepo.findById(command.getId())
                .orElseThrow(() -> new ItemNotFound("Stop"));

        Stop source = command.getStop();

        // Update fields
        existing.setTitle(source.getTitle());
        existing.setDescription(source.getDescription());
        existing.setLatitude(source.getLatitude() != null ? source.getLatitude() : 0.0);
        existing.setLongitude(source.getLongitude() != null ? source.getLongitude() : 0.0);

        // Crucial for reordering itinerary stops
        if (source.getStopOrder() != null) {
            existing.setStopOrder(source.getStopOrder());
        }

        stopRepo.save(existing);
        return ResponseEntity.ok(new StopDto(existing));
    }

    public ResponseEntity<Void> delete(UUID id) {
        Optional<Stop> optionalStop = stopRepo.findById(id);
        if(optionalStop.isPresent()) {
            stopRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new ItemNotFound("Stop");
    }

    public ResponseEntity<List<StopDto>> findAll() {
        List<StopDto> stopDtos = stopRepo.findAll()
                .stream()
                .map(stopMapper::toDto)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(stopDtos);
    }

    public ResponseEntity<StopDto> findById(UUID id) {
        Optional<Stop> optionalStop = stopRepo.findById(id);
        if(optionalStop.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(stopMapper.toDto(optionalStop.get()));
        }
        throw new ItemNotFound("Stop");
    }

}
