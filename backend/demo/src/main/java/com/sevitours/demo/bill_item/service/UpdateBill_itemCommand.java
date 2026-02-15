package com.sevitours.demo.bill_item.service;

import com.sevitours.demo.bill_item.model.Bill_item;
import lombok.Getter;

@Getter
public class UpdateBill_itemCommand {
    private Long id;
    private Bill_item billItem;

    public UpdateBill_itemCommand(Long id, Bill_item billItem) {
        this.id = id;
        this.billItem = billItem;
    }
}
