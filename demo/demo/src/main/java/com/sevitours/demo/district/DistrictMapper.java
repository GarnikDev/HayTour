package com.sevitours.demo.district;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DistrictMapper {
    @Mapping(source = "id", target = "id")
    DistrictDto toDto(District entity);

    @Mapping(target = "id", ignore = true)
    District toEntity(DistrictDto dto);
}