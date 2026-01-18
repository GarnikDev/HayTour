package com.sevitours.demo.tour;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TourMapper {
    @Mapping(source = "guide.id", target = "guideId")
    TourDto toDto(Tour entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "guide", ignore = true)
    Tour toEntity(TourDto dto);
}
