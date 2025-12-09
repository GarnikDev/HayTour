package com.sevitours.demo.rental.rental_bike;


import com.sevitours.demo.bicycle.Bicycle;
import com.sevitours.demo.rental.Rental;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"Rental_Bike\"")
@Data
@IdClass(RentalBikeId.class)
public class RentalBike {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"Rental_id\"", nullable = false)
    private Rental rental;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"Bicycle_id\"", nullable = false)
    private Bicycle bicycle;
}
