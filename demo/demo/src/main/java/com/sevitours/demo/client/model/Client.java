package com.sevitours.demo.client.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"Client\"")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Id\"", nullable = false)
    private Integer id;

    @Column(name = "\"Name\"", nullable = false)
    private String name;

    @Column(name = "\"Email\"",  nullable = false)
    private String email;

    @Column(name = "\"Phone\"", nullable = false)
    private String phone;

    @Column(name = "\"Country\"", nullable = false)
    private String country;

    @Column(name = "\"Language\"", nullable = false)
    private String language;

    public Client() {
    }

    public Client(Integer id, String name, String email, String phone, String country, String language) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.language = language;
    }
}
