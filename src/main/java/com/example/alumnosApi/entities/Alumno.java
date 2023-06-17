package com.example.alumnosApi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Builder
@AllArgsConstructor
@Entity(name = "alumno")
@Data
public class Alumno implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Size(max=20, min = 0)
    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String nombre;

    @NonNull
    @Size(max=20, min = 0)
    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String apellido;

    @Min(0)
    @Max(100)
    private int edad;

    @Email
    private String email;

    private String password;

    private boolean adeudaMateriasSecundario;

    @NonNull
    private boolean abonoMatricula;

    @Min(0)
    @Max(10)
    private double notaExamenIngreso;

    @NonNull
    private Roles rol = Roles.ALUMNO;
    public Alumno() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    //antes tenía un bug porque me faltaba el constructor sin argumentos
}
