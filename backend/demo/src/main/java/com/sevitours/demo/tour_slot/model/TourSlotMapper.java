package com.sevitours.demo.tour_slot.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TourSlotMapper {

    @Mapping(source = "tourOffer.id", target = "tourOfferId")
    TourSlotDto toDto(TourSlot tourSlot);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "tourOfferId", target = "tourOffer.id")
    TourSlot toEntity(TourSlotDto tourSlotDto);

}
