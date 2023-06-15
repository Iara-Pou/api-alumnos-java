package com.example.alumnosApi.controllers;

import com.example.alumnosApi.entities.Alumno;
import com.example.alumnosApi.services.AlumnoServices;
import com.example.alumnosApi.services.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    @Autowired
    private AlumnoServices alumnoServices;
    @GetMapping
    public List obtenerAlumnos(@RequestParam(required = false) boolean ordenApellidoDescendiente,
                                       @RequestParam(required = false) boolean adeudanMaterias,
                                       @RequestParam(required = false) boolean abonoPendiente,
                                       @RequestParam(required = false) boolean promedioEdad,
                                       @RequestParam(required = false) boolean mayorNota) {

        if (ordenApellidoDescendiente) return alumnoServices.ordenarDecrecientementePorApellido();
        if (adeudanMaterias) return alumnoServices.obtenerAlumnosQueAdeudanMaterias();
        if (abonoPendiente) return alumnoServices.obtenerAlumnosQueNoAbonaron();
        if (promedioEdad) return Collections.singletonList(alumnoServices.obtenerPromedioEdades());
        if (mayorNota) return alumnoServices.obtenerAlumnoConMayorNota();

        return alumnoServices.mostrarAlumnos();

    }

    @PostMapping("/crear")
    public Response sumarAlumno(@RequestBody Alumno alumno){
        return alumnoServices.sumarAlumno(alumno);
    }

    @PutMapping("/actualizar/{id}")
    public Response modificarAlumno(@RequestBody Alumno alumnoActualizado, @PathVariable("id") Long id){
        return alumnoServices.editarAlumno(id, alumnoActualizado);
    }

    @DeleteMapping ("/borrar/{id}")
    public Response borrarAlumno(@PathVariable("id") Long id){
        return alumnoServices.borrarAlumno(id);
    }

    @GetMapping("*")
    public String rutaNoEncontrada(){
        return "ERROR 404: La ruta a la que ingresó, no existe.";
    }

}
