package com.sevitours.demo.bill_item.model;

import com.sevitours.demo.bill_item.enums.Billing;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Bill_itemDto {

    private Long id;
    private Long bill_id;
    private Integer quantity;
    private String description;
    private Double unitPrice;
    private Double amount;
    private Boolean refundable;
    private OffsetDateTime createdAt;
    private Billing billing;

    public Bill_itemDto(Bill_item billitem) {
        if(billitem != null){
            this.id = billitem.getId();
            this.bill_id = billitem.getBill().getId();
            this.quantity = billitem.getQuantity();
            this.description = billitem.getDescription();
            this.unitPrice = billitem.getUnitPrice();
            this.amount = billitem.getAmount();
            this.refundable = billitem.getRefundable();
            this.createdAt = billitem.getCreatedAt();
            this.billing = billitem.getBilling();
        }
    }
}
