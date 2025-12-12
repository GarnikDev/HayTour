package com.sevitours.demo.tour.services;

import com.sevitours.demo.Query;
import com.sevitours.demo.tour.Tour;
import com.sevitours.demo.tour.TourDto;
import com.sevitours.demo.tour.TourMapper;
import com.sevitours.demo.tour.TourRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTourService implements Query<Void, List<TourDto>> {

    private final TourRepository tourRepository;
    private final TourMapper tourMapper;

    public GetTourService(TourRepository tourRepository, TourMapper tourMapper) {
        this.tourRepository = tourRepository;
        this.tourMapper = tourMapper;
    }

    @Override
    public ResponseEntity<List<TourDto>> execute(Void input){
        List<TourDto> tourDtos = tourRepository.findAll()
                .stream()
                .map(tourMapper::toDto)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(tourDtos);
    }
}
