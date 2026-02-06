package com.sevitours.demo.bill.service;

import com.sevitours.demo.bill.model.Bill;
import lombok.Getter;

@Getter
public class UpdateBillCommand {
    private Long id;
    private Bill bill;

    public UpdateBillCommand(Bill bill, Long id) {
        this.bill = bill;
        this.id = id;
    }
}
