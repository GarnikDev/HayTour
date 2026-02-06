package com.sevitours.demo.tour;

import com.sevitours.demo.bicycle.Bicycle;
import com.sevitours.demo.common.enums.TourType;
import com.sevitours.demo.guide.Guide;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
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
    private OffsetDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"Guide_id\"", nullable = false)
    private Guide guide;

    @Enumerated(EnumType.STRING)
    @Column(name = "\"Type\"")
    private TourType type;  // Or TourType enum
}
