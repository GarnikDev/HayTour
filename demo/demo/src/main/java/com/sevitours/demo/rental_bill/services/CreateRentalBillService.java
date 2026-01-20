package com.sevitours.demo.rental_bill.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.rental_bill.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateRentalBillService implements Command<RentalBill, RentalBillDto> {

    private final RentalBillRepository repository;

    public CreateRentalBillService(RentalBillRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<RentalBillDto> execute(RentalBill bill) {
        RentalBill saved = repository.save(bill);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RentalBillDto(saved));
    }
}
