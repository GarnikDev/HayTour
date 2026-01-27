package com.sevitours.demo.bill;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BillMapper {
    @Mapping(source = "client.id", target ="clientId")
    BillDto toDto(Bill entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    Bill toEntity(BillDto dto);
}
