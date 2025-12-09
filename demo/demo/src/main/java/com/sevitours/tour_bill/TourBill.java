package com.sevitours.tour_bill;

import com.sevitours.client.Client;
import com.sevitours.rental_bill.RentalBill_Id;
import com.sevitours.tour.Tour;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "\"Rental_Bill\"")
@Data
@IdClass(RentalBill_Id.class)
public class TourBill {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"Tour_id\"", nullable = false)
    private Tour tour;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"Client_id\"", nullable = false)
    private Client client;

    @Column(name = "\"Adult_amount\"")
    private Float AdultAmount;

    @Column(name = "\"Kid_amount\"")
    private Float KidAmount;

    @Column(name = "\"IVA\"")
    private Float iva;
}
