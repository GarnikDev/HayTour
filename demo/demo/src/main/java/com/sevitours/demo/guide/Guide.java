package com.sevitours.demo.guide;

import com.sevitours.demo.district.District;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "\"Guide\"")
@Data
public class Guide {
    @Id
    @Column(name = "\"Id\"")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"District_id\"", nullable = false)
    private District district;

    @Column(name = "\"Id_number\"")
    private String idNumber;

    @Column(name = "\"Name\"")
    private String name;

    @Column(name = "\"Email\"")
    private String email;

    @Column(name = "\"Phone\"")
    private String phone;

    @Column(name = "\"Stars\"")
    private Integer stars;

    @Column(name = "\"Start_date\"")
    private LocalDate startDate;
}