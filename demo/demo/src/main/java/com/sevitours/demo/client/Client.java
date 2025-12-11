package com.sevitours.demo.client;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"Client\"")
@Data
public class Client {

    @Id
    @Column(name = "\"Id\"", nullable = false)
    private Integer id;

    @Column(name = "\"Name\"", nullable = false)
    private String name;

    @Column(name = "\"Email\"", nullable = false)
    private String email;

    @Column(name = "\"Phone\"", nullable = false)
    private String phone;

    @Column(name = "\"Country\"")
    private String country;

    @Column(name = "\"Language\"", nullable = false)
    private String language;
}
