package com.sevitours.demo.tour_bill.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.tour_bill.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateTourBillService implements Command<UpdateTourBillCommand, TourBillDto> {

    private final TourBillRepository tourBillRepository;

    public UpdateTourBillService(TourBillRepository tourBillRepository) {
        this.tourBillRepository = tourBillRepository;
    }

    @Override
    public ResponseEntity<TourBillDto> execute(UpdateTourBillCommand command) {
        Optional<TourBill> optional = tourBillRepository.findById(command.getId());
        if (optional.isPresent()) {
            TourBill bill = command.getTourBill();
            bill.setTour(command.getTourBill().getTour()); // I think this line will cause me bunch of problems, not sure though
            tourBillRepository.save(bill);
            return ResponseEntity.ok(new TourBillDto(bill));
        }
        return null;
    }
}
