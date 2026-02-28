package com.sevitours.demo.tour.model;

import com.sevitours.demo.tour.enums.TourType;
import com.sevitours.demo.guide.model.Guide;
import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "\"Tour\"")
@Data
public class Tour {
    @Id
    @Column(name = "\"Id\"")
    private UUID id;

    @Column(name = "\"Time\"")
    private OffsetDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"Guide_id\"", nullable = false)
    private Guide guide;

    @Enumerated(EnumType.STRING)
    @Column(name = "\"Type\"")
    private TourType type;
}
