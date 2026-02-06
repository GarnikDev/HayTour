package com.sevitours.demo.bill_item.model;


import com.sevitours.demo.bill_item.enums.Billing;
import com.sevitours.demo.bill.model.Bill;
import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
@Table(name = "\"Bill_item\"")
@Data
public class Bill_item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"id\"", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id", nullable = false)
    private Bill bill;

    @Column(name = "\"quantity\"", nullable = false)
    private Integer quantity;

    @Column(name = "\"description\"")
    private String description;

    @Column(name = "\"unit_price\"", nullable = false)
    private Double unitPrice;

    @Column(name = "\"amount\"", nullable = false)
    private Double amount;

    @Column(name = "\"refundable\"", nullable = false)
    private Boolean refundable;

    @Column(name = "\"created_at\"", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "\"type\"", nullable = false, columnDefinition = "pricing_type")
    @Enumerated(EnumType.STRING)
    private Billing billing;

}
