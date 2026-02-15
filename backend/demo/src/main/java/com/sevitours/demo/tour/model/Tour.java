package com.sevitours.demo.tour.model;

import com.sevitours.demo.tour.enums.TourType;
import com.sevitours.demo.guide.model.Guide;
import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
@Table(name = "\"Tour\"")
@Data
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Id\"")
    private Integer id;

    @Column(name = "\"Time\"")
    private OffsetDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"Guide_id\"", nullable = false)
    private Guide guide;

    @Enumerated(EnumType.STRING)
    @Column(name = "\"Type\"")
    private TourType type;
}
