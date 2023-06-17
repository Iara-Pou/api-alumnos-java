package com.example.alumnosApi.DTO;

import com.example.alumnosApi.entities.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private boolean abonoMatricula;
    private Roles rol;
}
