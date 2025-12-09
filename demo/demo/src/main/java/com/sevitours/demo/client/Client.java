package com.sevitours.demo.client;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"Client\"")
@Data
public class Client {
    @Id
    @Column(name = "\"Id\"")
    private Integer id;

    @Column(name = "\"Name\"")
    private String name;

    @Column(name = "\"Email\"")
    private String email;

    @Column(name = "\"Phone\"")
    private String phone;

    @Column(name = "\"Country\"")
    private String country;

    @Column(name = "\"Language\"")
    private String language;
}
