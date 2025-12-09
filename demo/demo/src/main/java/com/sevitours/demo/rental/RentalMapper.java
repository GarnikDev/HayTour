package com.sevitours.demo.rental;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RentalMapper {
    RentalDto toDto(Rental entity);

    @Mapping(target = "id", ignore = true)
    Rental toEntity(RentalDto dto);
}
