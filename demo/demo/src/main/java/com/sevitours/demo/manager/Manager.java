package com.sevitours.demo.manager;

import com.sevitours.demo.district.District;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "\"Manager\"")
@Data
public class Manager {
    @Id
    @Column(name = "\"Id\"")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY) // Might need to be changed to a OneToOne depending on future needs
    @JoinColumn(name = "\"District_id\"", nullable = false)
    private District district;

    @Column(name = "\"Id_number\"")
    private String idNumber;

    @Column(name = "\"Name\"")
    private String name;

    @Column(name = "\"Phone\"")
    private String phone;

    @Column(name = "\"Start_date\"")
    private LocalDate startDate;
}
