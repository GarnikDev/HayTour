package com.sevitours.demo.bill;
import lombok.Getter;

@Getter
public class UpdateBillCommand {
    private Integer id;
    private Bill bill;

    public UpdateBillCommand(Bill bill, Integer id) {
        this.bill = bill;
        this.id = id;
    }
}
