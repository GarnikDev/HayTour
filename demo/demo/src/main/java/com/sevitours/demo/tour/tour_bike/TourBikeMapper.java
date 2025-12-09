package com.sevitours.demo.tour.tour_bike;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TourBikeMapper {
    @Mapping(source = "tour.id", target = "tourId")
    @Mapping(source = "bicycle.id", target = "bicycleId")
    TourBikeDto toDto(TourBike entity);

    @Mapping(target = "tour", ignore = true)
    @Mapping(target = "bicycle", ignore = true)
    TourBike toEntity(TourBikeDto dto);
}