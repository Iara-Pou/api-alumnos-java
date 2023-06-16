package com.example.alumnosApi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.NonNull;

import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@Entity(name = "alumno")
@Data
public class Alumno {
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

    private boolean adeudaMateriasSecundario;

    @NonNull
    private boolean abonoMatricula;

    @Min(0)
    @Max(10)
    private double notaExamenIngreso;

    public Alumno() {
    }
    //antes tenía un bug porque me faltaba el constructor sin argumentos
}
