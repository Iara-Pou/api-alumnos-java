package com.example.alumnosApi.services;

import com.example.alumnosApi.entities.Alumno;
import com.example.alumnosApi.entities.Curso;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.regex.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CursoServices {

    Curso curso = new Curso();
    List<Alumno> listaAlumnos = curso.getAlumnos();

    public List mostrarAlumnos() {
        return ordenarDecrecientementePorApellido(this.curso);
    }

    public String sumarAlumno(Alumno alumno) {
        if (validarAlumno(alumno).isBlank()) {
            listaAlumnos.add(alumno);
            return "¡El usuario " + alumno.getNombre() + " " + alumno.getApellido() + " se ha ingresado correctamente!";
        } else {
            return validarAlumno(alumno);
        }
    }


    public String editarAlumno(Alumno alumno) {
        try {
            borrarAlumno(alumno.getId());
            sumarAlumno(alumno);
            return "¡El usuario " + alumno.getNombre() + " " + alumno.getApellido() + " se ha modificado correctamente!";
        } catch (Exception e) {
            return "La operación falló. Revisar los datos ingresados, ¡Por favor!";
        }
    }

    public String borrarAlumno(int id) {
        List alumnosPedidos = listaAlumnos.stream().filter(alumno -> alumno.getId() == id).toList();

        if (alumnosPedidos.size() >= 1) {
            alumnosPedidos.forEach(alumno -> listaAlumnos.remove(alumno));
            return "¡Operación realizada con éxito!";
        } else {
            return "ERROR: No existe ningún alumno con ese id.";
        }

    }

    private String validarAlumno(Alumno alumno) {

        if (Objects.isNull(alumno.getApellido()) ||
                Objects.isNull(alumno.getNombre()) ||
                Objects.isNull(alumno.isAbonoMatricula()) ||
                Objects.isNull(alumno.getEdad()) ||
                Objects.isNull(alumno.getNotaExamenIngreso()) ||
                Objects.isNull(alumno.isAdeudaMateriasSecundario()))
            return "ERROR: Ningún campo debe estar vacío.";

        if (alumno.getEdad() < 0 || alumno.getEdad() > 100)
            return "ERROR: La edad no puede ser numero negativo ni mayor a 100.";
        if (alumno.getApellido().length() > 20 || alumno.getNombre().length() > 20)
            return "ERROR: Ni el nombre ni el apellido debe exceder los 20 carácteres";
        if(!validarLetrasYEspacios(alumno.getApellido()) || !validarLetrasYEspacios(alumno.getNombre()))
            return "ERROR: El nombre y el apellido deben contener solo letras mayúsculas, minúsculas y espacios.";
        if (alumno.getNotaExamenIngreso() < 1 || alumno.getNotaExamenIngreso() > 10)
            return "ERROR: La nota debe ir del 1 al 10";

        return "";
    }

    public boolean validarLetrasYEspacios(String campo) {
        String regex = "^[a-zA-Z ]+$";
        return campo.matches(regex);
    }
    public List<Alumno> ordenarDecrecientementePorApellido(Curso curso) {
        return listaAlumnos.stream().sorted(Comparator.comparing((Alumno a) -> a.getApellido()).reversed()).toList();
    }

    public double obtenerPromedioEdades() {
        double totalEdades = listaAlumnos.stream().mapToInt(Alumno::getEdad).sum();

        return totalEdades / listaAlumnos.size();
    }


    public List<Alumno> obtenerAlumnosQueAdeudanMaterias() {
        return listaAlumnos.stream().filter(a -> a.isAdeudaMateriasSecundario() == true).toList();
    }

    public List<Alumno> obtenerAlumnosQueNoAbonaron() {
        return listaAlumnos.stream().filter(a -> a.isAbonoMatricula() == false).toList();
    }

    public List<Alumno> obtenerAlumnoConMayorNota() {
        double notaMaxima = listaAlumnos.stream().mapToDouble(Alumno::getNotaExamenIngreso).max().orElse(0);
        return listaAlumnos.stream().filter(a -> a.getNotaExamenIngreso() == notaMaxima).collect(Collectors.toList());
    }

}
