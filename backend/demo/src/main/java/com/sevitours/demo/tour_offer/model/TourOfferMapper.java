package com.sevitours.demo.tour_offer.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TourOfferMapper {
    @Mapping(source = "appUser.id", target = "appUserId")
    TourOfferDto toDto(TourOffer entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "imageUrl", ignore = true)
    TourOffer toEntity(TourOfferDto dto);

}
