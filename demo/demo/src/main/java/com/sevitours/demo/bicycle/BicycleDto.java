package com.sevitours.demo.bicycle;

import com.sevitours.demo.common.enums.BikeType;
import lombok.Data;

@Data
public class BicycleDto {
    private Integer id;
    private Integer districtId;
    private BikeType type;

    public BicycleDto(Bicycle bicycle) {
    }
}