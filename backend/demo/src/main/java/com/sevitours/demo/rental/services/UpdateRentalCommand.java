package com.sevitours.demo.rental.services;

import com.sevitours.demo.rental.model.Rental;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateRentalCommand {
    private UUID id;
    private Rental rental;

    public UpdateRentalCommand(UUID id, Rental rental) {
        this.id = id;
        this.rental = rental;
    }
}