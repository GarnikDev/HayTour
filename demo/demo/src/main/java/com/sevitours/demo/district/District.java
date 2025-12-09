package com.sevitours.demo.district;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"District\"")
@Data
public class District {
    @Id
    @Column(name = "\"Id\"")
    private Integer id;

    @Column(name = "\"Region\"")
    private String region;

    @Column(name = "\"Type\"")
    private String type;

    @Column(name = "\"Adress\"")  // Note typo in dump: Adress vs Address
    private String address;

    @Column(name = "\"Email\"")
    private String email;
}
