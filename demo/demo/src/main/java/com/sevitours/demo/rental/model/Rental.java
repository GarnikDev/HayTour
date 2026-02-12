package com.sevitours.demo.rental.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
@Table(name = "\"Rental\"")
@Data
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Id\"")
    private Integer id;

    @Column(name = "\"Duration\"")
    private Integer duration;  // Assuming smallint as minutes or hours

    @Column(name = "\"Time\"")
    private OffsetDateTime time;  // TIMESTAMP without time zone

    @Column(name = "\"Adult_count\"")
    private Integer adultCount;

    @Column(name = "\"Kid_count\"")
    private Integer kidCount;
}
