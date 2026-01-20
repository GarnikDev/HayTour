package com.sevitours.demo.rental.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.rental.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateRentalService implements Command<UpdateRentalCommand, RentalDto> {

    private final RentalRepository rentalRepository;

    public UpdateRentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Override
    public ResponseEntity<RentalDto> execute(UpdateRentalCommand command) {
        Optional<Rental> optional = rentalRepository.findById(command.getId());
        if (optional.isPresent()) {
            Rental rental = command.getRental();
            rental.setId(command.getId());
            rentalRepository.save(rental);
            return ResponseEntity.ok(new RentalDto(rental));
        }
        return null;
    }
}
