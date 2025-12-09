package com.sevitours.rental;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "\"Rental\"")
@Data
public class Rental {
    @Id
    @Column(name = "\"Id\"")
    private Integer id;

    @Column(name = "\"Duration\"")
    private Integer duration;  // Assuming smallint as minutes or hours

    @Column(name = "\"Adult_count\"")
    private Integer adultCount;

    @Column(name = "\"Kid_count\"")
    private Integer kidCount;

    @Column(name = "\"Time\"")
    private Timestamp date;  // TIMESTAMP without time zone
}
