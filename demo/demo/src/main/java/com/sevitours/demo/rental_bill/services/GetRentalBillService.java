package com.sevitours.demo.rental_bill.services;

import com.sevitours.demo.Query;
import com.sevitours.demo.rental_bill.RentalBillDto;
import com.sevitours.demo.rental_bill.RentalBillMapper;
import com.sevitours.demo.rental_bill.RentalBillRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetRentalBillService implements Query<Void, List<RentalBillDto>> {

    private final RentalBillRepository rentalBillRepository;
    private final RentalBillMapper rentalBillMapper;

    public GetRentalBillService(RentalBillRepository rentalBillRepository, RentalBillMapper rentalBillMapper) {
        this.rentalBillRepository = rentalBillRepository;
        this.rentalBillMapper = rentalBillMapper;
    }

    @Override
    public ResponseEntity<List<RentalBillDto>> execute(Void input){
        List<RentalBillDto> rentalBillDtos = rentalBillRepository.findAll()
                .stream()
                .map(rentalBillMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(rentalBillDtos);
    }

}
