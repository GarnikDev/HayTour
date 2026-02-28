package com.sevitours.demo.tour.model;

import com.sevitours.demo.tour.enums.TourType;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class TourDto {
    private UUID id;
    private OffsetDateTime time;
    private UUID guideId;
    private TourType type;

    public TourDto(Tour tour) {
        if (tour != null) {
            this.id = tour.getId();
            this.time = tour.getTime();
            this.guideId = tour.getGuide().getId();
            this.type = tour.getType();
        }
    }
}
