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

    @Column(name = "\"Country\"")
    private String country;

    @ManyToOne
    @JoinColumn(name = "\"Language\"")
    private Language language;

    @OneToOne
    @JoinColumn(name = "\"user_id\"", nullable = false)
    private AppUser user;

    public Customer() {
    }

}
