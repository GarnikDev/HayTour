package com.sevitours.demo.user.model;

import com.sevitours.demo.user.enums.AppUserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "{user.name.notblank}")
    @Column(name = "\"Name\"", nullable = false)
    private String name;

    @Email(message = "{user.email.email}")
    @Column(name = "\"Email\"",  nullable = false, unique = true)
    private String email;

    @NotBlank(message = "{user.phone.notblank}")
    @Column(name = "\"Phone\"", nullable = false)
    private String phone;

    @NotBlank(message = "{user.idnumber.notblank}")
    @Column(name = "\"Id_number\"")
    private String idNumber;

    @NotBlank(message = "{user.password.notblank}")
    @Column(name = "\"Password\"", nullable = false)
    private String password;

    @NotNull
    @Column(name = "\"Enabled\"", nullable = false)
    private Boolean enabled;

    @NotNull
    @Column(name = "\"Created_at\"", nullable = false)
    private OffsetDateTime createdAt;

    @NotNull
    @Column(name = "\"Role\"", nullable = false)
    @Enumerated(EnumType.STRING)
    private AppUserRole role;

}
