package com.sevitours.demo.stop.model;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class StopDto {

    private UUID id;
    private UUID tourOfferId;
    private String title;
    private String description;
    private Double latitude;
    private Double longitude;
    private Integer stopOrder;
    private OffsetDateTime createdAt;

    public StopDto(Stop stop) {
        this.id = stop.getId();
        this.tourOfferId = stop.getTourOffer().getId();
        this.title = stop.getTitle();
        this.description = stop.getDescription();
        this.latitude = stop.getLatitude();
        this.longitude = stop.getLongitude();
        this.stopOrder = stop.getStopOrder();
        this.createdAt = stop.getCreatedAt();
    }
}
