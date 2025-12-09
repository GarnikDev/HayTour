package com.sevitours.demo.rental;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class RentalDto {
    private Integer id;
    private Integer duration;
    private Integer adultCount;
    private Integer kidCount;
    private Timestamp date;
}
