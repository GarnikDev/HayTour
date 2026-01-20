package com.sevitours.demo.rental_bill;

import lombok.Getter;

@Getter
public class UpdateRentalBillCommand {
    private RentalBill_Id id;
    private RentalBill rentalBill;

    public UpdateRentalBillCommand(RentalBill_Id id, RentalBill rentalBill) {
        this.id = id;
        this.rentalBill = rentalBill;
    }
}