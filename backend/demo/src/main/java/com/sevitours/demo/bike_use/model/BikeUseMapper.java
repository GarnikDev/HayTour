package com.sevitours.demo.bike_use.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BikeUseMapper {

    @Mapping(source = "bicycle.id", target = "bicycleId")
    @Mapping(source = "tour.id", target = "tourId")
    @Mapping(source = "rental.id", target = "rentalId")
    BikeUseDto toDto(BikeUse entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bicycle", ignore = true)
    @Mapping(target = "tour", ignore = true)
    @Mapping(target = "rental", ignore = true)
    BikeUse toEntity(BikeUseDto dto);

}
