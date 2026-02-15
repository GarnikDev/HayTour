package com.sevitours.demo.client.model;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(source = "language.id", target = "languageId")
    CustomerDto toDto(Customer entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "language", ignore = true)
    Customer toEntity(CustomerDto dto);
}