package com.sevitours.language;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"Language\"")
@Data
public class Language {
    @Id
    @Column(name = "\"Id\"")
    private Integer id;

    @Column(name = "\"Language\"")
    private String language;
}
