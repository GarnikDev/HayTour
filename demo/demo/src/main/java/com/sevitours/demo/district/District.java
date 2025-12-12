package com.sevitours.demo.district;

import com.sevitours.demo.common.enums.DistrictType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"District\"")
@Data
public class District {
    @Id
    @Column(name = "\"Id\"", nullable = false)
    private Integer id;

    @Column(name = "\"Region\"", nullable = false)
    private String region;

    @Column(name = "\"Type\"", nullable = false)
    @Enumerated(EnumType.STRING)
    private DistrictType type;

    @Column(name = "\"Address\"", nullable = false)
    private String address;

    @Column(name = "\"Email\"", nullable = false)
    private String email;

}
