package com.sevitours.demo.bill.service;

import com.sevitours.demo.bill.model.Bill;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateBillCommand {
    private UUID id;
    private Bill bill;

    public UpdateBillCommand(Bill bill, UUID id) {
        this.bill = bill;
        this.id = id;
    }
}
