package com.sevitours.demo.rental.services;

import com.sevitours.demo.rental.model.Rental;
import lombok.Getter;

@Getter
public class UpdateRentalCommand {
    private Integer id;
    private Rental rental;

    public UpdateRentalCommand(Integer id, Rental rental) {
        this.id = id;
        this.rental = rental;
    }
}