package com.sevitours.demo.rental_bill;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RentalBillMapper {
    RentalBillDto toDto(RentalBill entity);

    @Mapping(target = "rental", ignore = true)
    @Mapping(target = "client", ignore = true)
    RentalBill toEntity(RentalBillDto dto);
}
