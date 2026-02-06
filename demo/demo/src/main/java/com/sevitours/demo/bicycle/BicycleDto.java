package com.sevitours.demo.bicycle;

import com.sevitours.demo.common.enums.BikeType;
import com.sevitours.demo.common.enums.Condition;
import lombok.Data;

@Data
public class BicycleDto {
    private Long id;
    private Integer districtId;
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