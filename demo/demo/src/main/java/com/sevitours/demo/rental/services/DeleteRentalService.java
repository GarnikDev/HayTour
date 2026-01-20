package com.sevitours.demo.rental.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.rental.Rental;
import com.sevitours.demo.rental.RentalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteRentalService implements Command<Integer, Void> {

    private final RentalRepository rentalRepository;

    public DeleteRentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Integer id) {
        Optional<Rental> rental = rentalRepository.findById(id);
        if (rental.isPresent()) {
            rentalRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return null;
    }
}
