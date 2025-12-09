package com.sevitours.demo.tour_bill;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TourBillMapper {
    TourBillDto toDto(TourBill entity);

    @Mapping(target = "tour", ignore = true)
    @Mapping(target = "client", ignore = true)
    TourBill toEntity(TourBillDto dto);
}
