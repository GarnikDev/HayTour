package com.sevitours.demo.bicycle.model;

import com.sevitours.demo.bicycle.enums.BikeType;
import com.sevitours.demo.common.enums.Condition;
import lombok.Data;

import java.util.UUID;

@Data
public class BicycleDto {
    private UUID id;
    private UUID districtId;
    private BikeType type;
    private Condition condition;

    public BicycleDto(Bicycle bicycle) {
        if(bicycle != null) {
            this.id = bicycle.getId();
            this.districtId = bicycle.getDistrict().getId();
            this.type = bicycle.getType();
            this.condition = bicycle.getCondition();
        }
    }
}