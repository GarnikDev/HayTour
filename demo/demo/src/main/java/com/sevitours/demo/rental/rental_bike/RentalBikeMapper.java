package com.sevitours.demo.rental.rental_bike;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RentalBikeMapper {
    @Mapping(source = "rental.id", target = "rentalId")
    @Mapping(source = "bicycle.id", target = "bicycleId")
    RentalBikeDto toDto(RentalBike entity);

    @Mapping(target = "rental", ignore = true)
    @Mapping(target = "bicycle", ignore = true)
    RentalBike toEntity(RentalBike dto);
}
