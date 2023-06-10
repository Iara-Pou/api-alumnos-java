package com.example.alumnosApi.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Curso {

    private List<Alumno> alumnos = new ArrayList<>();

}