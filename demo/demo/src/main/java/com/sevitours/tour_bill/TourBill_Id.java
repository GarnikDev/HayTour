package com.sevitours.tour_bill;

import java.io.Serializable;

import com.sevitours.client.Client;
import com.sevitours.tour.Tour;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class TourBill_Id implements Serializable {
    private Tour tour;
    private Client client;

    public TourBill_Id() {}

    public TourBill_Id(Tour tour, Client client) {
        this.tour = tour;
        this.client = client;
    }
}
