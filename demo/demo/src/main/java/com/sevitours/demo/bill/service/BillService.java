package com.sevitours.demo.bill.service;

import com.sevitours.demo.bill.model.Bill;
import com.sevitours.demo.bill.model.BillDto;
import com.sevitours.demo.bill.model.BillMapper;
import com.sevitours.demo.bill.repo.BillRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    private final BillRepository billRepository;
    private final BillMapper billMapper;

    public BillService(BillRepository billRepository, BillMapper billMapper) {
        this.billRepository = billRepository;
        this.billMapper = billMapper;
    }

    //Create
    public ResponseEntity<BillDto> create(Bill bill) {
        Bill savedBill = billRepository.save(bill);

        return ResponseEntity.status(HttpStatus.CREATED).body(new  BillDto(savedBill));
    }

    //Get
    public ResponseEntity<List<BillDto>> getAll(Void input) { // Get all bills
        List<BillDto> billsDto = billRepository.findAll()
                .stream()
                .map(billMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(billsDto);
    }

    //Get a Bill by Id
    public ResponseEntity<BillDto> getById(Long input){ // Get bill by ID
        Optional<Bill> billOptional = billRepository.findById(input);
        if (billOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(billMapper.toDto(billOptional.get()));
        }else  {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //Delete a bill by id
    public ResponseEntity<Void> deleteById(Long id) {

        Optional<Bill> optionalBill = billRepository.findById(id);
        if(optionalBill.isPresent()) {
            billRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return null;
    }

    //Update
    public ResponseEntity<BillDto> update(UpdateBillCommand command) {
        Optional<Bill> optionalBill = billRepository.findById(command.getId());
        if (optionalBill.isPresent()) {
            Bill bill = command.getBill();
            bill.setId(command.getId());
            billRepository.save(bill);
            return ResponseEntity.ok(new BillDto(bill));
        }

        return null;
    }

}
