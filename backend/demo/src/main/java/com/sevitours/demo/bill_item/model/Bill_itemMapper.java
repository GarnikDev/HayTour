package com.sevitours.demo.bill_item.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface Bill_itemMapper {
    @Mapping(source = "bill.id", target = "bill_id")
    Bill_itemDto toDto(Bill_item entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bill", ignore = true)
    Bill_item toEntity(Bill_itemDto dto);

}
