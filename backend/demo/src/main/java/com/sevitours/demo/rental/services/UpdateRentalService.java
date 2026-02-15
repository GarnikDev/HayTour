package com.sevitours.demo.rental.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.common.ItemNotFound;
import com.sevitours.demo.rental.model.Rental;
import com.sevitours.demo.rental.model.RentalDto;
import com.sevitours.demo.rental.repo.RentalRepository;
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
        throw new ItemNotFound("Rental");
    }
}
