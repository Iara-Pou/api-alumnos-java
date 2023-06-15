package com.example.alumnosApi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import static jakarta.persistence.GenerationType.IDENTITY;


@AllArgsConstructor
@Entity(name = "alumno")
@Data
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private String apellido;
    private int edad;

    private boolean adeudaMateriasSecundario;
    private boolean abonoMatricula;
    private double notaExamenIngreso;

    public Alumno() {
    }
}
