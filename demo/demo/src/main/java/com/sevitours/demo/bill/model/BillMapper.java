package com.sevitours.demo.bill.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BillMapper {
    @Mapping(source = "customer.id", target ="customerId")
    BillDto toDto(Bill entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    Bill toEntity(BillDto dto);
}
