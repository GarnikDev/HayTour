package com.sevitours.demo.rental.services;

import com.sevitours.demo.Query;
import com.sevitours.demo.rental.RentalController;
import com.sevitours.demo.rental.RentalDto;
import com.sevitours.demo.rental.RentalMapper;
import com.sevitours.demo.rental.RentalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetRentalService implements Query<Void, List<RentalDto>> {

    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;

    public GetRentalService(RentalRepository rentalRepository, RentalMapper rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
    }

    @Override
    public ResponseEntity<List<RentalDto>> execute(Void input){
        List<RentalDto> rentalDtos = rentalRepository.findAll()
                .stream()
                .map(rentalMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(rentalDtos);
    }

}
