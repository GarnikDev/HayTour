package com.sevitours.rental.rental_bike;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RentalBikeMapper {
    RentalBikeDto toDto(RentalBike entity);
    RentalBike toEntity(RentalBike dto);
}
