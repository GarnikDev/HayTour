package com.sevitours.demo.tour;

import com.sevitours.demo.bicycle.Bicycle;
import com.sevitours.demo.common.enums.TourType;
import com.sevitours.demo.district.District;
import com.sevitours.demo.guide.Guide;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "\"Tour\"")
@Data
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Id\"")
    private Integer id;

    @Column(name = "\"Time\"")
    private Timestamp time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"Guide_id\"", nullable = false)
    private Guide guide;

    @Column(name = "\"Adult_count\"")
    private Integer adultCount;

    @Column(name = "\"Kid_count\"")
    private Integer kidCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "\"Type\"")
    private TourType type;  // Or TourType enum

    @ManyToMany
    @JoinTable(name = "\"Tour_Bike\"",
            joinColumns = @JoinColumn(name = "\"Tour_id\""),
            inverseJoinColumns = @JoinColumn(name = "\"Bicycle_id\""))
    private List<Bicycle> bikes;
}
