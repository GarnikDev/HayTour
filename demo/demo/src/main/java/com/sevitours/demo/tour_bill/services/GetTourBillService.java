package com.sevitours.demo.tour_bill.services;

import com.sevitours.demo.Query;
import com.sevitours.demo.tour.TourRepository;
import com.sevitours.demo.tour_bill.TourBillDto;
import com.sevitours.demo.tour_bill.TourBillMapper;
import com.sevitours.demo.tour_bill.TourBillRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTourBillService implements Query<Void, List<TourBillDto>> {

    private final TourBillRepository tourBillRepository;
    private final TourBillMapper tourBillMapper;

    public GetTourBillService(TourBillRepository tourBillRepository, TourBillMapper tourBillMapper) {
        this.tourBillRepository = tourBillRepository;
        this.tourBillMapper = tourBillMapper;
    }

    @Override
    public ResponseEntity<List<TourBillDto>> execute(Void input){
        List<TourBillDto> tourBillDtos =  tourBillRepository.findAll()
                .stream()
                .map(tourBillMapper::toDto)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(tourBillDtos);
    }
}
