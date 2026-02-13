package com.sevitours.demo.bill.model;

import com.sevitours.demo.bill.enums.Currency;
import com.sevitours.demo.common.enums.Source;
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
    private Integer customerId;
    private Currency currency;
    private OffsetDateTime created_at;

    public BillDto(Bill bill) {
        if(bill != null) {
            this.id = bill.getId();
            this.subtotal = bill.getSubtotal();
            this.status = bill.getStatus();
            this.sourceType = bill.getSourceType();
            this.sourceId = bill.getSourceId();
            this.customerId = bill.getCustomer().getId();
            this.currency = bill.getCurrency();
            this.created_at = bill.getCreated_at();
        }
    }
}
