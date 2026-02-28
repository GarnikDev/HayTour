package com.sevitours.demo.bill_item.service;

import com.sevitours.demo.bill_item.model.Bill_item;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateBill_itemCommand {
    private UUID id;
    private Bill_item billItem;

    public UpdateBill_itemCommand(UUID id, Bill_item billItem) {
        this.id = id;
        this.billItem = billItem;
    }
}
