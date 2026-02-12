package com.sevitours.demo.tour.services;

import com.sevitours.demo.Query;
import com.sevitours.demo.tour.model.Tour;
import com.sevitours.demo.tour.model.TourDto;
import com.sevitours.demo.tour.model.TourMapper;
import com.sevitours.demo.tour.repo.TourRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetTourService implements Query<Void, List<TourDto>> {

    private final TourRepository tourRepository;
    private final TourMapper tourMapper;
    public GetTourService(TourRepository tourRepository, TourMapper tourMapper) {
        this.tourRepository = tourRepository;
        this.tourMapper = tourMapper;
    }

    @Override
    public ResponseEntity<List<TourDto>> execute(Void input) {
        List<TourDto> tours = tourRepository.findAll()
                .stream()
                .map(tourMapper::toDto)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(tours);
    }

    public ResponseEntity<TourDto> execute(Integer id) {
        Optional<Tour> tour = tourRepository.findById(id);
        if (tour.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(tourMapper.toDto(tour.get()));
        }
        return null;
    }
}
