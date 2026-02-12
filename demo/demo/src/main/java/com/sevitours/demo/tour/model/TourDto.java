package com.sevitours.demo.tour.model;

import com.sevitours.demo.tour.enums.TourType;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class TourDto {
    private Integer id;
    private OffsetDateTime time;
    private Integer guideId;
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
