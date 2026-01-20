package com.sevitours.demo.rental;

import lombok.Data;
import java.sql.Timestamp;
import java.time.OffsetDateTime;

@Data
public class RentalDto {
    private Integer id;
    private Integer duration;
    private Integer adultCount;
    private Integer kidCount;
    private OffsetDateTime time;

    public RentalDto(Rental rental) {
    }
}
