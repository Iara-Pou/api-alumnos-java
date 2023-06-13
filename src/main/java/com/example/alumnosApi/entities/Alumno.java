package com.example.alumnosApi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Entity
public class Alumno {
    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String apellido;
    private int edad;

    private boolean adeudaMateriasSecundario;
    private boolean abonoMatricula;
    private double notaExamenIngreso;
}
