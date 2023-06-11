package com.example.alumnosApi.controllers;

import com.example.alumnosApi.entities.Alumno;
import com.example.alumnosApi.services.CursoServices;
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
    public String sumarAlumno(@RequestBody Alumno alumno){
        try{
            return cursoServices.sumarAlumno(alumno);
        } catch (Exception e){
            return "No se pudo cargar correctamente al alumno ¡Revisar los datos ingresados, por favor!";
        }
    }

    @PutMapping("/cambiarAlumno")
    public String modificarAlumno(@RequestBody Alumno alumno){
        return cursoServices.editarAlumno(alumno);
    }

    @DeleteMapping ("/borrarAlumno/{id}")
    public String borrarAlumno(@PathVariable("id") int id){
        return cursoServices.borrarAlumno(id);
    }

}
