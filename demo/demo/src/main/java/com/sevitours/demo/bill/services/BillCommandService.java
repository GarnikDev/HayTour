package com.sevitours.demo.bill.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.bill.Bill;
import com.sevitours.demo.bill.BillDto;
import org.springframework.http.ResponseEntity;

public class BillCommandService implements Command<Bill, BillDto> {



    @Override
    public ResponseEntity<BillDto> execute(Bill input) {
        return null;
    }
}
