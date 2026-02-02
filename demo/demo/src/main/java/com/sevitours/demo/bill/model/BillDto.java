package com.sevitours.demo.bill.model;

import com.sevitours.demo.bill.enums.Currency;
import com.sevitours.demo.bill.enums.Source;
import com.sevitours.demo.bill.enums.Status;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class BillDto {

    private Long id;
    private Double subtotal;
    private Status status;
    private Source sourceType;
    private Long sourceId;
    private Long clientId;
    private Currency currency;
    private OffsetDateTime created_at;

    public BillDto(Bill bill) {
    }
}
