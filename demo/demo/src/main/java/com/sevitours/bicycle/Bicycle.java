package com.sevitours.bicycle;

import com.sevitours.common.enums.BikeType;
import com.sevitours.district.District;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"Bicycle\"")  // Quoted for case-sensitivity
@Data
public class Bicycle {
    @Id
    @Column(name = "\"Id\"")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"District_id\"", nullable = false)
    private District district;

    @Column(name = "\"Type\"")
    private BikeType type;  // Or BikeType enum: @Enumerated(EnumType.STRING)
}
