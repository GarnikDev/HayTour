package com.sevitours.demo.bill_item.service;

import com.sevitours.demo.bill_item.model.Bill_item;
import com.sevitours.demo.bill_item.model.Bill_itemDto;
import com.sevitours.demo.bill_item.model.Bill_itemMapper;
import com.sevitours.demo.bill_item.repo.Bill_itemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Bill_itemService {

    private final Bill_itemRepository bill_itemRepository;
    private final Bill_itemMapper bill_itemMapper;

    public Bill_itemService(Bill_itemRepository bill_itemRepository,
                                 Bill_itemMapper bill_itemMapper) {
        this.bill_itemRepository = bill_itemRepository;
        this.bill_itemMapper = bill_itemMapper;
    }

    //Create
    public ResponseEntity<Bill_itemDto> create(Bill_item bill_item) {
        Bill_item savedBill_item = bill_itemRepository.save(bill_item);

        return ResponseEntity.status(HttpStatus.CREATED).body(new  Bill_itemDto(savedBill_item));
    }

    //Get
    public ResponseEntity<List<Bill_itemDto>> getAll(Void input) { // Get all bill_items
        List<Bill_itemDto> bill_itemsDto = bill_itemRepository.findAll()
                .stream()
                .map(bill_itemMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(bill_itemsDto);
    }

    //Get a Bill_item by Id
    public ResponseEntity<Bill_itemDto> getById(Long input){ // Get bill_item by ID
        Optional<Bill_item> bill_itemOptional = bill_itemRepository.findById(input);
        if (bill_itemOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(bill_itemMapper.toDto(bill_itemOptional.get()));
        }else  {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //Delete a bill_item by id
    public ResponseEntity<Void> deleteById(Long id) {

        Optional<Bill_item> optionalBill_item = bill_itemRepository.findById(id);
        if(optionalBill_item.isPresent()) {
            bill_itemRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return null;
    }

    //Update
    public ResponseEntity<Bill_itemDto> update(UpdateBill_itemCommand command) {
        Optional<Bill_item> optionalBill_item = bill_itemRepository.findById(command.getId());
        if (optionalBill_item.isPresent()) {
            Bill_item bill_item = command.getBillItem();
            bill_item.setId(command.getId());
            bill_itemRepository.save(bill_item);
            return ResponseEntity.ok(new Bill_itemDto(bill_item));
        }

        return null;
    }

}
