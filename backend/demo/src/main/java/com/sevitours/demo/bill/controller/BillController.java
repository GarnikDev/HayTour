package com.sevitours.demo.bill.controller;


import com.sevitours.demo.bill.model.Bill;
import com.sevitours.demo.bill.model.BillDto;
import com.sevitours.demo.bill.service.BillService;
import com.sevitours.demo.bill.service.UpdateBillCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bills")
public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/bill")
    public ResponseEntity<BillDto> createBill(@RequestBody Bill bill) {
        return billService.create(bill);
    }

    @GetMapping("/view")
    public ResponseEntity<List<BillDto>> getAllBills() {
        Void input = null;
        return billService.getAll(input);
    }

    @GetMapping("/view/id/{id}")
    public ResponseEntity<BillDto> getBillById(@PathVariable UUID id) {
        return billService.getById(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<BillDto> updateBill(@PathVariable UUID id, @RequestBody Bill bill) {
        return billService.update(new UpdateBillCommand(bill, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable UUID id) {
        return billService.deleteById(id);
    }

}
