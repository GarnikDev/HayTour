package com.sevitours.demo.client.model;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDto toDto(Client entity);

    @Mapping(target = "id", ignore = true)
    Client toEntity(ClientDto dto);
}