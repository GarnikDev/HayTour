package com.sevitours.demo.rental.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class RentalDto {
    private Integer id;
    private Integer duration;
    private Integer adultCount;
    private Integer kidCount;
    private OffsetDateTime time;

    public RentalDto(Rental rental) {
        if(rental != null){
            this.id = rental.getId();
            this.duration = rental.getDuration();
            this.adultCount = rental.getAdultCount();
            this.kidCount = rental.getKidCount();
            this.time = rental.getTime();
        }
    }
}
