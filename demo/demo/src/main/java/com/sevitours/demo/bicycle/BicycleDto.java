package com.sevitours.demo.bicycle;

import com.sevitours.demo.common.enums.BikeType;
import lombok.Data;

@Data
public class BicycleDto {
    private Integer id;
    private Integer districtId;
    private BikeType type;

    public BicycleDto(Bicycle bicycle) {
        /*
        this.id = bicycle.getId();
        this.type = bicycle.getType();

        if (bicycle.getDistrict() != null) {
            this.districtId = bicycle.getDistrict().getId();
        }
         */
    }
}