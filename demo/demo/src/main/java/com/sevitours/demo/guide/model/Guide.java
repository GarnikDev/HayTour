package com.sevitours.demo.guide.model;

import com.sevitours.demo.district.model.District;
import com.sevitours.demo.user.model.AppUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "\"Guide\"")
@Data
public class Guide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Id\"")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"District_id\"", nullable = false)
    private District district;

    @Column(name = "\"Stars\"")
    private Integer stars;

    @Column(name = "\"Start_date\"")
    private LocalDate startDate;

    @OneToOne
    @JoinColumn(name = "\"user_id\"")
    private AppUser user;
}