package com.sevitours.demo.tour.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.common.ItemNotFound;
import com.sevitours.demo.tour.model.Tour;
import com.sevitours.demo.tour.model.TourDto;
import com.sevitours.demo.tour.repo.TourRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateTourService implements Command<UpdateTourCommand, TourDto> {

    private final TourRepository tourRepository;

    public UpdateTourService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public ResponseEntity<TourDto> execute(UpdateTourCommand command) {
        Optional<Tour> optional = tourRepository.findById(command.getId());
        if (optional.isPresent()) {
            Tour tour = command.getTour();
            tour.setId(command.getId());
            tourRepository.save(tour);
            return ResponseEntity.ok(new TourDto(tour));
        }
        throw new ItemNotFound("Tour");
    }
}
