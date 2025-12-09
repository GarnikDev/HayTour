package com.sevitours.tour;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TourMapper {
    @Mapping(source = "guide.id", target = "guideId")
    @Mapping(source = "district.id", target = "districtId")
    TourDto toDto(Tour entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "guide", ignore = true)
    @Mapping(target = "district", ignore = true)
    Tour toEntity(TourDto dto);
}
