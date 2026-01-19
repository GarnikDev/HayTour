package com.sevitours.demo.language;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LanguageMapper {

    LanguageDto toDto(Language entity);

    @Mapping(target = "id", ignore = true)
    Language toEntity(LanguageDto dto);
}
