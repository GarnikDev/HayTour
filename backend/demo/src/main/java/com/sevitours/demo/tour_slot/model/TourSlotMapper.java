package com.sevitours.demo.tour_slot.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TourSlotMapper {

    @Mapping(source = "tour.id", target = "tourId")
    TourSlotDto toDto(TourSlot tourSlot);

    @Mapping(target = "id", ignore = true)
    TourSlot toEntity(TourSlotDto tourSlotDto);

}
