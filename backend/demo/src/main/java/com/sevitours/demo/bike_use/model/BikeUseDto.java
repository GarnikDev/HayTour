package com.sevitours.demo.bike_use.model;

import com.sevitours.demo.common.enums.Condition;
import com.sevitours.demo.common.enums.Source;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class BikeUseDto {
    private UUID id;
    private Source useType;
    private UUID tourId;
    private UUID rentalId;
    private UUID bicycleId;
    private OffsetDateTime assignedAt;
    private OffsetDateTime returnedAt;
    private Condition conditionBefore;
    private Condition conditionAfter;
    private String notes;

    public BikeUseDto(BikeUse bikeUse) {
        this.id = bikeUse.getId();
        this.useType = bikeUse.getUseType();
        this.tourId = bikeUse.getTour().getId();
        this.rentalId = bikeUse.getRental().getId();
        this.bicycleId = bikeUse.getBicycle().getId();
        this.assignedAt = bikeUse.getAssignedAt();
        this.returnedAt = bikeUse.getReturnedAt();
        this.conditionBefore = bikeUse.getConditionBefore();
        this.conditionAfter = bikeUse.getConditionAfter();
        this.notes = bikeUse.getNotes();
    }
}
