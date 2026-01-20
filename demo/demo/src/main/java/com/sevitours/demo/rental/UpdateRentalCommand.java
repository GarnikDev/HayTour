package com.sevitours.demo.rental;

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