package com.example.alumnosApi.controllers;

import com.example.alumnosApi.entities.Alumno;
import com.example.alumnosApi.services.CursoServices;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping
public class CursoController {
    @Autowired
    private CursoServices cursoServices;
    @GetMapping("/todos")
    public List alumnos(){
        return cursoServices.mostrarAlumnos();
    }

    @GetMapping("/promedioEdad")
    public double mostrarPromedioEdad(){
        return cursoServices.obtenerPromedioEdades();
    }

    @GetMapping("/alumnosQueAdeudanMaterias")
    public List<Alumno> mostrarAlumnosQueAdeudanMaterias(){
        return cursoServices.obtenerAlumnosQueAdeudanMaterias();
    }

    @GetMapping("/alumnoConMayorNota")
    public List<Alumno> mostrarAlumnoConMayorNota(){
        return cursoServices.obtenerAlumnoConMayorNota();
    }

    @GetMapping("/alumnosAbonoPendiente")
    public List<Alumno> mostrarAlumnosAbonoPendiente(){
        return cursoServices.obtenerAlumnosQueNoAbonaron();
    }

    @PostMapping("/sumarAlumno")
    public String sumarAlumno(@RequestBody String infoalumno){
        Gson g = new Gson();
        Alumno alumno = g.fromJson(infoalumno, Alumno.class);

        return cursoServices.sumarAlumno(alumno);
    }

    @PutMapping("/cambiarAlumno")
    public String modificarAlumno(@RequestBody String infoalumno){
        Gson g = new Gson();
        Alumno alumno = g.fromJson(infoalumno, Alumno.class);

        return cursoServices.editarAlumno(alumno);
    }

    @DeleteMapping ("/borrarAlumno/{id}")
    public String borrarAlumno(@PathVariable("id") int id){
        return cursoServices.borrarAlumno(id);
    }

}
