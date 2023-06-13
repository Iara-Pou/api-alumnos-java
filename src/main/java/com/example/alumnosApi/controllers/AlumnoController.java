package com.example.alumnosApi.controllers;

import com.example.alumnosApi.entities.Alumno;
import com.example.alumnosApi.services.AlumnoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping
public class AlumnoController {
    @Autowired
    private AlumnoServices alumnoServices;
    @GetMapping("/todos")
    public List alumnos(@RequestParam boolean adeudaMaterias){
        if (adeudaMaterias) return alumnoServices.obtenerAlumnosQueAdeudanMaterias();
        return alumnoServices.mostrarAlumnos();
    }
//Esta consulta se espera que sea de tipo booleano y se puede pasar en la URL,
//por ejemplo, /todos?adeudaMaterias=true.

    @GetMapping("/promedioEdad")
    public double mostrarPromedioEdad(){
        return alumnoServices.obtenerPromedioEdades();
    }

    @GetMapping("/alumnosQueAdeudanMaterias")
    public List<Alumno> mostrarAlumnosQueAdeudanMaterias(){
        return alumnoServices.obtenerAlumnosQueAdeudanMaterias();
    }

    @GetMapping("/alumnoConMayorNota")
    public List<Alumno> mostrarAlumnoConMayorNota(){
        return alumnoServices.obtenerAlumnoConMayorNota();
    }

    @GetMapping("/alumnosAbonoPendiente")
    public List<Alumno> mostrarAlumnosAbonoPendiente(){
        return alumnoServices.obtenerAlumnosQueNoAbonaron();
    }

    @PostMapping("/sumarAlumno")
    public String sumarAlumno(@RequestBody Alumno alumno){
        try{
        return alumnoServices.sumarAlumno(alumno);
        } catch (Exception e) {
            return "ERROR: los datos no están ingresados en el formato indicado.";
        }
    }

    @PutMapping("/cambiarAlumno")
    public String modificarAlumno(@RequestBody Alumno alumno){
        try {
            return alumnoServices.editarAlumno(alumno);
        } catch (Exception e){
            return "ERROR: El alumno no existe en sistema!";
        }
    }

    @DeleteMapping ("/borrarAlumno/{id}")
    public String borrarAlumno(@PathVariable("id") Long id){
        try{
        return alumnoServices.borrarAlumno(id);
        } catch (Exception e){
            return "ERROR: no se ingresó un numero para el id.";
        }
    }

    @GetMapping("*")
    public String rutaNoEncontrada(){
        return "ERROR 404: La ruta a la que ingresó, no existe.";
    }

}
