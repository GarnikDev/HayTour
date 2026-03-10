package com.sevitours.demo.tour_price.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TourPriceMapper {

    @Mapping(source = "tourOffer.id", target = "tourOfferId")
    TourPriceDto toDto(TourPrice tourOffer);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "tourOfferId", target = "tourOffer.id")
    TourPrice toEntity(TourPriceDto tourOfferDto);

}
