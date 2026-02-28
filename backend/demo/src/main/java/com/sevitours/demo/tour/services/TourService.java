package com.sevitours.demo.tour.services;

import com.sevitours.demo.common.ItemNotFound;
import com.sevitours.demo.tour.model.Tour;
import com.sevitours.demo.tour.model.TourDto;
import com.sevitours.demo.tour.model.TourMapper;
import com.sevitours.demo.tour.repo.TourRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TourService {

    private final TourRepository tourRepository;
    private final TourMapper tourMapper;

    public TourService(TourRepository tourRepository,
                       TourMapper tourMapper) {
        this.tourRepository = tourRepository;
        this.tourMapper = tourMapper;
    }

    public ResponseEntity<TourDto> create(Tour tour) {
        Tour savedTour = tourRepository.save(tour);
        return ResponseEntity.status(HttpStatus.CREATED).body(new  TourDto(savedTour));
    }

    public ResponseEntity<List<TourDto>> getAll() {
        List<TourDto> tours = tourRepository.findAll()
                .stream()
                .map(tourMapper::toDto)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(tours);
    }

    public ResponseEntity<TourDto> getById(UUID id) {
        Optional<Tour> tour = tourRepository.findById(id);
        if (tour.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(tourMapper.toDto(tour.get()));
        }
        throw new ItemNotFound("Tour");
    }

    public ResponseEntity<TourDto> update(UpdateTourCommand command) {
        Optional<Tour> optional = tourRepository.findById(command.getId());
        if (optional.isPresent()) {
            Tour tour = command.getTour();
            tour.setId(command.getId());
            tourRepository.save(tour);
            return ResponseEntity.ok(new TourDto(tour));
        }
        throw new ItemNotFound("Tour");
    }

    public ResponseEntity<Void> delete(UUID id) {
        Optional<Tour> tour = tourRepository.findById(id);
        if (tour.isPresent()) {
            tourRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new ItemNotFound("Tour");
    }
}
