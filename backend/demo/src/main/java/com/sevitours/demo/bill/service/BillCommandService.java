package com.sevitours.demo.bill.service;

import com.sevitours.demo.Command;
import com.sevitours.demo.bill.model.Bill;
import com.sevitours.demo.bill.model.BillDto;
import org.springframework.http.ResponseEntity;

public class BillCommandService implements Command<Bill, BillDto> {



    @Override
    public ResponseEntity<BillDto> execute(Bill input) {
        return null;
    }
}
