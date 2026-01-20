package com.sevitours.demo.rental.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.rental.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateRentalService implements Command<Rental, RentalDto> {

    private final RentalRepository rentalRepository;

    public CreateRentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Override
    public ResponseEntity<RentalDto> execute(Rental rental) {
        Rental saved = rentalRepository.save(rental);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RentalDto(saved));
    }
}
