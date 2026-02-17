package com.sevitours.demo.user.model;

import com.sevitours.demo.user.enums.AppUserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="\"AppUser\"")
@Data
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "supabase_id", unique = true)
    private String supabaseId;

    @NotBlank(message = "{user.name.notblank}")
    @Column(name = "\"Username\"", nullable = false)
    private String username;

    @Email(message = "{user.email.email}")
    @Column(name = "\"Email\"",  nullable = false, unique = true)
    private String email;


    @Column(name = "\"Phone\"")
    private String phone;


    @Column(name = "\"Id_number\"")
    private String idNumber;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
