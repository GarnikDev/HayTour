package com.sevitours.demo.rental.rental_bike;

import com.sevitours.demo.bicycle.Bicycle;
import com.sevitours.demo.rental.Rental;
import lombok.Data;

@Data
public class RentalBikeDto {
    private Rental rental;
    private Bicycle bicycle;
}
