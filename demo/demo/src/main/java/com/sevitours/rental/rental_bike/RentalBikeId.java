package com.sevitours.rental.rental_bike;

import com.sevitours.bicycle.Bicycle;
import com.sevitours.rental.Rental;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class RentalBikeId implements Serializable {
    private Rental rental;
    private Bicycle bicycle;
}
