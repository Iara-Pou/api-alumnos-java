package com.example.alumnosApi.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Alumno {
    private int id;
    private String nombre;
    private String apellido;
    private int edad;

    private boolean adeudaMateriasSecundario;
    private boolean abonoMatricula;
    private double notaExamenIngreso;
}
