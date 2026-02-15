package com.sevitours.demo.guide.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GuideMapper {
    @Mapping(source = "district.id", target = "districtId")
    GuideDto toDto(Guide entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "district", ignore = true)
    Guide toEntity(GuideDto dto);
}
