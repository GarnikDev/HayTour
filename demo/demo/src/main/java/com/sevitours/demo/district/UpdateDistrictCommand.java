package com.sevitours.demo.district;

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
