package com.sevitours.rental.rental_bike;


import com.sevitours.bicycle.Bicycle;
import com.sevitours.rental.Rental;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"Rental_Bike\"")
@Data
@IdClass(RentalBikeId.class)
public class RentalBike {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"Rental_Id\"", nullable = false)
    private Rental rental;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"Bicycle_Id\"", nullable = false)
    private Bicycle bicycle;
}
