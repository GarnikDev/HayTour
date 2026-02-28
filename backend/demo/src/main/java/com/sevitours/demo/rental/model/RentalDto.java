package com.sevitours.demo.rental.model;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class RentalDto {
    private UUID id;
    private Integer duration;
    private OffsetDateTime time;

    public RentalDto(Rental rental) {
        if(rental != null){
            this.id = rental.getId();
            this.duration = rental.getDuration();
            this.time = rental.getTime();
        }
    }
}
