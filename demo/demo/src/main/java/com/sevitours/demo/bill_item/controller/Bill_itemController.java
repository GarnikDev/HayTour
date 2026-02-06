package com.sevitours.demo.bill_item.controller;

import com.sevitours.demo.bill_item.model.Bill_item;
import com.sevitours.demo.bill_item.model.Bill_itemDto;
import com.sevitours.demo.bill_item.service.Bill_itemService;
import com.sevitours.demo.bill_item.service.UpdateBill_itemCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill_items")
public class Bill_itemController {

    private final Bill_itemService bill_itemService;

    public Bill_itemController(Bill_itemService bill_itemService) {
        this.bill_itemService = bill_itemService;
    }

    @PostMapping("/bill_item")
    public ResponseEntity<Bill_itemDto> createBill_item(@RequestBody Bill_item bill_item) {
        return bill_itemService.create(bill_item);
    }

    @GetMapping("/view")
    public ResponseEntity<List<Bill_itemDto>> getAllBill_items() {
        Void input = null;
        return bill_itemService.getAll(input);
    }

    @GetMapping("/view/id/{id}")
    public ResponseEntity<Bill_itemDto> getBill_itemById(@PathVariable Long id) {
        return bill_itemService.getById(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Bill_itemDto> updateBill_item(@PathVariable Long id, @RequestBody Bill_item bill_item) {
        return bill_itemService.update(new UpdateBill_itemCommand(id, bill_item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill_item(@PathVariable Long id) {
        return bill_itemService.deleteById(id);
    }


}
