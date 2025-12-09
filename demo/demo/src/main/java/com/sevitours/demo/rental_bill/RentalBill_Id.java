package com.sevitours.demo.rental_bill;

import java.io.Serializable;

import com.sevitours.demo.client.Client;
import com.sevitours.demo.rental.Rental;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class RentalBill_Id implements Serializable {
    private Rental rental;
    private Client client;

    public RentalBill_Id() {}

    public RentalBill_Id(Rental rental, Client client) {
        this.rental = rental;
        this.client = client;
    }
}
