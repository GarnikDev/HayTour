package com.sevitours.demo.user.model;

import com.sevitours.demo.user.enums.AppUserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
@Table(name="\"AppUser\"")
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)

    private Long id;

    @Column(name = "\"Name\"", nullable = false)
    private String name;

    @Column(name = "\"Email\"",  nullable = false, unique = true)
    private String email;

    @Column(name = "\"Phone\"", nullable = false)
    private String phone;

    @Column(name = "\"Id_number\"")
    private String idNumber;

    @Column(name = "\"Password\"", nullable = false)
    private String password;

    @Column(name = "\"Enabled\"", nullable = false)
    private Boolean enabled;

    @Column(name = "\"Created_at\"", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "\"Role\"", nullable = false)
    @Enumerated(EnumType.STRING)
    private AppUserRole role;

}
