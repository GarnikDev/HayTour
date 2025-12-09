package com.sevitours.demo.tour;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TourDto {
    private Integer id;
    private Timestamp time;
    private Integer guideId;
    private Integer districtId;
    private Integer adultCount;
    private Integer kidCount;
    private String type;
}
