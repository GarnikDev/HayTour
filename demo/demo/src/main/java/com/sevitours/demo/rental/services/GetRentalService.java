package com.sevitours.demo.rental.services;

import com.sevitours.demo.Query;
import com.sevitours.demo.rental.model.Rental;
import com.sevitours.demo.rental.model.RentalDto;
import com.sevitours.demo.rental.model.RentalMapper;
import com.sevitours.demo.rental.repo.RentalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetRentalService implements Query<Void, List<RentalDto>> {

    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;
    public GetRentalService(RentalRepository rentalRepository, RentalMapper rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
    }

    @Override
    public ResponseEntity<List<RentalDto>> execute(Void input) {
        List<RentalDto> dtos = rentalRepository.findAll()
                .stream()
                .map(rentalMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    public ResponseEntity<RentalDto> execute(Integer id) {
        Optional<Rental> rental = rentalRepository.findById(id);
        if (rental.isPresent()) {
            return ResponseEntity.ok(rentalMapper.toDto(rental.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
