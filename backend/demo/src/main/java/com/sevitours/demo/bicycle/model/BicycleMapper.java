package com.sevitours.demo.bicycle.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BicycleMapper {
    @Mapping(source = "district.id", target = "districtId")
    BicycleDto toDto(Bicycle entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "district", ignore = true)
    Bicycle toEntity(BicycleDto dto);
}