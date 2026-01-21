package com.sevitours.demo.tour.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.tour.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteTourService implements Command<Integer, Void> {

    private final TourRepository tourRepository;

    public DeleteTourService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Integer id) {
        Optional<Tour> tour = tourRepository.findById(id);
        if (tour.isPresent()) {
            tourRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return null;
    }
}
