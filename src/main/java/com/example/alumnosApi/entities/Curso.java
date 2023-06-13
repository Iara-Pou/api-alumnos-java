package com.example.alumnosApi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Curso {

    private List<Alumno> alumnos = new ArrayList<>();

}