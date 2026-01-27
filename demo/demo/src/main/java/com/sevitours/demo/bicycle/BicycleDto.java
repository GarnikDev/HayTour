package com.sevitours.demo.bicycle;

import com.sevitours.demo.common.enums.BikeType;
import com.sevitours.demo.common.enums.Condition;
import lombok.Data;

@Data
public class BicycleDto {
    private Integer id;
    private Integer districtId;
    private BikeType type;
    private Condition condition;

    public BicycleDto(Bicycle bicycle) {
    }
}