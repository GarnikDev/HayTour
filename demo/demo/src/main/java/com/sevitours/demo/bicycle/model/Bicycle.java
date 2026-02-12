package com.sevitours.demo.bicycle.model;

import com.sevitours.demo.bicycle.enums.BikeType;
import com.sevitours.demo.common.enums.Condition;
import com.sevitours.demo.district.model.District;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"Bicycle\"")  // Quoted for case-sensitivity
@Data
public class Bicycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Id\"", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"District_id\"", nullable = false)
    private District district;

    @Column(name = "\"Type\"", nullable = false, columnDefinition = "bike_types")
    @Enumerated(EnumType.STRING)
    private BikeType type;

    @Column(name = "\"condition\"", nullable = false, columnDefinition = "bike_condition")
    @Enumerated(EnumType.STRING)
    private Condition condition;

    protected Bicycle() {
    }

    public Bicycle(District district, BikeType type) {
        this.district = district;
        this.type = type;
    }
}
