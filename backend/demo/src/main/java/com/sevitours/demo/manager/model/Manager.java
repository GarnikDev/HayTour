package com.sevitours.demo.manager.model;

import com.sevitours.demo.district.model.District;
import com.sevitours.demo.user.model.AppUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "\"Manager\"")
@Data
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Id\"")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY) // Might need to be changed to a OneToOne depending on future needs
    @JoinColumn(name = "\"District_id\"", nullable = false)
    private District district;

    @Column(name = "\"Start_date\"")
    private LocalDate startDate;

    @OneToOne
    @JoinColumn(name = "\"user_id\"")
    private AppUser user;
}
