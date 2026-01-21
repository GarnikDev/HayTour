package com.sevitours.demo.tour_bill.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.tour_bill.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateTourBillService implements Command<TourBill, TourBillDto> {

    private final TourBillRepository tourBillRepository;

    public CreateTourBillService(TourBillRepository tourBillRepository) {
        this.tourBillRepository = tourBillRepository;
    }

    @Override
    public ResponseEntity<TourBillDto> execute(TourBill tourBill) {
        TourBill saved = tourBillRepository.save(tourBill);
        return ResponseEntity.status(HttpStatus.CREATED).body(new  TourBillDto(saved));
    }
}
