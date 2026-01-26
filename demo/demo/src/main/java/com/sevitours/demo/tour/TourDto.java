package com.sevitours.demo.tour;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class TourDto {
    private Integer id;
    private OffsetDateTime time;
    private Integer guideId;
    private Integer adultCount;
    private Integer kidCount;
    private String type;

    public TourDto(Tour tour) {
    }
}
