package com.sevitours.demo.district;

import com.sevitours.demo.common.enums.DistrictType;
import lombok.Data;

@Data
public class DistrictDto {
    private Integer id;
    private String region;
    private DistrictType type;
    private String address;
    private String email;
}
