package com.sevitours.demo.rental.services;

import com.sevitours.demo.common.ItemNotFound;
import com.sevitours.demo.rental.model.Rental;
import com.sevitours.demo.rental.model.RentalDto;
import com.sevitours.demo.rental.model.RentalMapper;
import com.sevitours.demo.rental.repo.RentalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;

    public RentalService(RentalRepository rentalRepository,
                         RentalMapper rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
    }

    public ResponseEntity<RentalDto> create(Rental rental) {
        Rental saved = rentalRepository.save(rental);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RentalDto(saved));
    }

    public ResponseEntity<List<RentalDto>> getAll() {
        List<RentalDto> dtos = rentalRepository.findAll()
                .stream()
                .map(rentalMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    public ResponseEntity<RentalDto> getById(UUID id) {
        Optional<Rental> rental = rentalRepository.findById(id);
        if (rental.isPresent()) {
            return ResponseEntity.ok(rentalMapper.toDto(rental.get()));
        }
        throw new ItemNotFound("Rental");
    }

    public ResponseEntity<RentalDto> update(UpdateRentalCommand command) {
        Optional<Rental> optional = rentalRepository.findById(command.getId());
        if (optional.isPresent()) {
            Rental rental = command.getRental();
            rental.setId(command.getId());
            rentalRepository.save(rental);
            return ResponseEntity.ok(new RentalDto(rental));
        }
        throw new ItemNotFound("Rental");
    }

    public ResponseEntity<Void> delete(UUID id) {
        Optional<Rental> rental = rentalRepository.findById(id);
        if (rental.isPresent()) {
            rentalRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new ItemNotFound("District");
    }
}
