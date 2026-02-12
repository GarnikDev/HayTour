package com.sevitours.demo.language.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"Language\"")
@Data
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Id\"")
    private Integer id;

    @Column(name = "\"Language\"")
    private String languageCode;
}
