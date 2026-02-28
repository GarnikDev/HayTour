package com.sevitours.demo.rental.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "\"Rental\"")
@Data
public class Rental {
    @Id
    @Column(name = "\"Id\"")
    private UUID id;

    @Column(name = "\"Duration\"")
    private Integer duration;  // Assuming smallint as minutes or hours

    @Column(name = "\"Time\"")
    private OffsetDateTime time;  // TIMESTAMP without time zone
}
