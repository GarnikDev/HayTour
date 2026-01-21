package com.sevitours.demo.tour_bill.services;

import com.sevitours.demo.Query;
import com.sevitours.demo.tour_bill.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetTourBillService implements Query<Void, List<TourBillDto>> {

    private final TourBillRepository tourBillRepository;
    private final TourBillMapper tourBillMapper;
    public GetTourBillService(TourBillRepository tourBillRepository, TourBillMapper tourBillMapper) {
        this.tourBillRepository = tourBillRepository;
        this.tourBillMapper = tourBillMapper;
    }

    @Override
    public ResponseEntity<List<TourBillDto>> execute(Void input) {
        List<TourBillDto> tourBillDtos = tourBillRepository.findAll()
                .stream()
                .map(tourBillMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(tourBillDtos);
    }

    public ResponseEntity<TourBillDto> execute(Integer id) {
        Optional<TourBill> bill = tourBillRepository.findById(id);
        if (bill.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(tourBillMapper.toDto(bill.get()));
        }
        return null;
    }
}
