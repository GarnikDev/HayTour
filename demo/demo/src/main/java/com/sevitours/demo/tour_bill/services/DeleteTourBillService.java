package com.sevitours.demo.tour_bill.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.tour_bill.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteTourBillService implements Command<Integer, Void> {

    private final TourBillRepository tourBillRepository;

    public DeleteTourBillService(TourBillRepository tourBillRepository) {
        this.tourBillRepository = tourBillRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Integer id) {
        Optional<TourBill> bill = tourBillRepository.findById(id);
        if (bill.isPresent()) {
            tourBillRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return null;
    }
}
