package com.sevitours.demo.bill.model;

import com.sevitours.demo.bill.enums.Currency;
import com.sevitours.demo.bill.enums.Source;
import com.sevitours.demo.bill.enums.Status;
import com.sevitours.demo.client.Client;
import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
@Table(name = "\"Bill\"")
@Data
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Id\"", nullable = false)
    private Long id;

    @Column(name = "\"total_amount\"", nullable = false)
    private Double totalAmount;

    @Column(name = "\"subtotal\"", nullable = false)
    private Double subtotal;

    @Column(name = "\"status\"", nullable = false, columnDefinition = "status_type")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "\"source_type\"", nullable = false, columnDefinition = "source_type")
    @Enumerated(EnumType.STRING)
    private Source sourceType;

    @Column(name = "\"source_id\"", nullable = false)
    private Long sourceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "\"currency\"", nullable = false, columnDefinition = "currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "\"created_at\"", nullable = false)
    private OffsetDateTime created_at;
}
