package com.sevitours.tour.tour_bike;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TourBikeMapper {
    TourBikeDto toDto(TourBike entity);

    TourBike toEntity(TourBikeDto dto);
}