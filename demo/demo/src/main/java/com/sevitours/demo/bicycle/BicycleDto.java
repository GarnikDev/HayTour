package com.sevitours.demo.bicycle;

import com.sevitours.demo.common.enums.BikeType;
import com.sevitours.demo.district.District;
import lombok.Data;

@Data
public class BicycleDto {
    private Integer id;
    private District district;
    private BikeType type;
}