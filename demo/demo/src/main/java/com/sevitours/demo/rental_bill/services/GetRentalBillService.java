package com.sevitours.demo.rental_bill.services;

import com.sevitours.demo.Query;
import com.sevitours.demo.rental_bill.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetRentalBillService implements Query<Void, List<RentalBillDto>> {

    private final RentalBillRepository repository;
    private final RentalBillMapper rentalBillMapper;

    public GetRentalBillService(RentalBillRepository repository, RentalBillMapper rentalBillMapper) {
        this.repository = repository;
        this.rentalBillMapper = rentalBillMapper;
    }

    @Override
    public ResponseEntity<List<RentalBillDto>> execute(Void input) {
        List<RentalBillDto> dtos = repository.findAll()
                .stream()
                .map(rentalBillMapper::toDto)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<RentalBillDto> execute(Integer id) {
        Optional<RentalBill> bill = repository.findById(id);
        if (bill.isPresent()) {
            return ResponseEntity.ok(rentalBillMapper.toDto(bill.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
