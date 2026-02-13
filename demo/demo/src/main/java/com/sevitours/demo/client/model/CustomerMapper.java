package com.sevitours.demo.client.model;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto toDto(Customer entity);

    @Mapping(target = "id", ignore = true)
    Customer toEntity(CustomerDto dto);
}