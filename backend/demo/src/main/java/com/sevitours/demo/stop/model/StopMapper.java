package com.sevitours.demo.stop.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StopMapper {
    @Mapping(source = "tourOffer.id", target = "tourOfferId")
    StopDto toDto(Stop entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tourOffer", ignore = true)
    Stop toEntity(StopDto dto);

}
