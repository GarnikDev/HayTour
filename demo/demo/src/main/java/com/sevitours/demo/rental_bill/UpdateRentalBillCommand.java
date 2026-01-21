package com.sevitours.demo.rental_bill;

import lombok.Getter;

@Getter
public class UpdateRentalBillCommand {
    private Integer id;
    private RentalBill rentalBill;

    public UpdateRentalBillCommand(Integer id, RentalBill rentalBill) {
        this.id = id;
        this.rentalBill = rentalBill;
    }
}