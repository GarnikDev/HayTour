package com.sevitours.bicycle;

import com.sevitours.common.enums.BikeType;
import lombok.Data;

@Data
public class BicycleDto {
    private Integer id;
    private Integer districtId;
    private BikeType type;
}