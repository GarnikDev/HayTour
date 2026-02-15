package com.sevitours.demo.client.model;

import com.sevitours.demo.language.model.Language;
import com.sevitours.demo.user.model.AppUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "\"Customer\"")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Id\"", nullable = false)
    private Integer id;

    @NotBlank
    @Column(name = "\"Country\"", nullable = false)
    private String country;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "\"Language\"", nullable = false)
    private Language language;

    @OneToOne
    @JoinColumn(name = "\"user_id\"", nullable = false)
    private AppUser user;

    public Customer() {
    }

}
