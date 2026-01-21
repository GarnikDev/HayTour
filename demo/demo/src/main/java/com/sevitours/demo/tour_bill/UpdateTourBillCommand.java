package com.sevitours.demo.tour_bill;

import lombok.Getter;

@Getter
public class UpdateTourBillCommand {
    private Integer id;
    private TourBill tourBill;

    public UpdateTourBillCommand(Integer id, TourBill tourBill) {
        this.id = id;
        this.tourBill = tourBill;
    }
}
