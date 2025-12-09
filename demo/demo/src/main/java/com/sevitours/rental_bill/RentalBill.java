package com.sevitours.rental_bill;

import com.sevitours.client.Client;
import com.sevitours.rental.Rental;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "\"Rental_Bill\"")
@Data
@IdClass(RentalBill_Id.class)
public class RentalBill {
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"Rental_id\"", nullable = false)
    private Rental rental;

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"Client_id\"", nullable = false)
    private Client client;

    @Column(name = "\"Adult_amount\"")
    private Float AdultAmount;

    @Column(name = "\"Kid_amount\"")
    private Float KidAmount;

    @Column(name = "\"IVA\"")
    private Float iva;
}
