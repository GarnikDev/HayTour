package com.sevitours.demo.rental.rental_bike;

import com.sevitours.demo.bicycle.Bicycle;
import com.sevitours.demo.rental.Rental;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class RentalBikeId implements Serializable {
    private Rental rental;
    private Bicycle bicycle;
}
