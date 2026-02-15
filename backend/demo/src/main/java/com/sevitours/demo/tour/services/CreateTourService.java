package com.sevitours.demo.tour.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.tour.model.Tour;
import com.sevitours.demo.tour.model.TourDto;
import com.sevitours.demo.tour.repo.TourRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateTourService implements Command<Tour, TourDto> {

    private final TourRepository tourRepository;

    public CreateTourService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public ResponseEntity<TourDto> execute(Tour tour) {
        Tour savedTour = tourRepository.save(tour);
        return ResponseEntity.status(HttpStatus.CREATED).body(new  TourDto(savedTour));
    }
}
