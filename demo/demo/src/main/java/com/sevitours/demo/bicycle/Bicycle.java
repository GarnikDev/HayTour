package com.sevitours.demo.bicycle;

import com.sevitours.demo.common.enums.BikeType;
import com.sevitours.demo.district.District;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"Bicycle\"")  // Quoted for case-sensitivity
@Data
public class Bicycle {
    @Id
    @Column(name = "\"Id\"", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"District_id\"", nullable = false)
    private District district;

    @Column(name = "\"Type\"", nullable = false)
    @Enumerated(EnumType.STRING)
    private BikeType type;  // Or BikeType enum: @Enumerated(EnumType.STRING)

    public Bicycle(District district, BikeType type) {
        this.district = district;
        this.type = type;
    }
}
