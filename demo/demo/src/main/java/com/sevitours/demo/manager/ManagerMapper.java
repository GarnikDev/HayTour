package com.sevitours.demo.manager;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
    @Mapping(source = "district.id", target = "districtId")
    ManagerDto toDto(Manager entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "district", ignore = true)
    Manager toEntity(ManagerDto dto);
}
