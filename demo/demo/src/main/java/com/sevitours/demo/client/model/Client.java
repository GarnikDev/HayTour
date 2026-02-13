package com.sevitours.demo.client.model;

import com.sevitours.demo.user.model.AppUser;
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

    @Column(name = "\"Country\"", nullable = false)
    private String country;

    @Column(name = "\"Language\"", nullable = false)
    private String language;

    @OneToOne
    @JoinColumn(name = "\"user_id\"")
    private AppUser user;

    public Client() {
    }

}
