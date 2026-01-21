package com.sevitours.demo.rental_bill.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.rental_bill.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteRentalBillService implements Command<Integer, Void> {

    private final RentalBillRepository repository;

    public DeleteRentalBillService(RentalBillRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<Void> execute(Integer id) {
        Optional<RentalBill> bill = repository.findById(id);
        if (bill.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return null;
    }
}
