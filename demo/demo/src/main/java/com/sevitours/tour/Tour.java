package com.sevitours.tour;

import com.sevitours.common.enums.TourType;
import com.sevitours.district.District;
import com.sevitours.guide.Guide;
import com.sevitours.rental.Rental;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "\"Tour\"")
@Data
public class Tour {
    @Id
    @Column(name = "\"Id\"")
    private Integer id;

    @Column(name = "\"Time\"")
    private Timestamp time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"Guide_id\"", nullable = false)
    private Guide guide;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"District_id\"", nullable = false)
    private District district;

    @Column(name = "\"Adult_count\"")
    private Integer adultCount;

    @Column(name = "\"Kid_count\"")
    private Integer kidCount;

    @Column(name = "\"Type\"")
    private TourType type;  // Or TourType enum
}
