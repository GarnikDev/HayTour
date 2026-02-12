package com.sevitours.demo.district.services;

import com.sevitours.demo.district.model.District;
import lombok.Getter;

@Getter
public class UpdateDistrictCommand {
    private Integer id;
    private District district;

    public UpdateDistrictCommand(Integer id, District district) {
        this.id = id;
        this.district = district;
    }
}
