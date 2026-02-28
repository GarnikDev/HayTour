package com.sevitours.demo.language.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "\"Language\"")
@Data
public class Language {
    @Id
    @Column(name = "\"Id\"")
    private UUID id;

    @Column(name = "\"Language\"")
    private String languageCode;
}
