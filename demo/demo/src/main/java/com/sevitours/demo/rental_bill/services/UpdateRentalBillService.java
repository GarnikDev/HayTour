package com.sevitours.demo.rental_bill.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.rental_bill.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateRentalBillService implements Command<UpdateRentalBillCommand, RentalBillDto> {

    private final RentalBillRepository repository;

    public UpdateRentalBillService(RentalBillRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<RentalBillDto> execute(UpdateRentalBillCommand command) {
        Optional<RentalBill> optional = repository.findById(command.getId());
        if (optional.isPresent()) {
            RentalBill bill = command.getRentalBill();
            bill.setRental(command.getRentalBill().getRental()); // I think this line will cause me bunch of problems, not sure though
            repository.save(bill);
            return ResponseEntity.ok(new RentalBillDto(bill));
        }
        return null;
    }
}