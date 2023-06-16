package com.example.alumnosApi.services;

import com.example.alumnosApi.entities.Alumno;
import com.example.alumnosApi.entities.Curso;
import com.example.alumnosApi.exceptions.IdInvalidoException;
import com.example.alumnosApi.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AlumnoServices {

    Curso curso = new Curso();
    //    List<Alumno> listaAlumnos = curso.getAlumnos();
    @Autowired
    private AlumnoRepository alumnoRepository;

    //INYECTAR EL REPO POR CONSTRUCTOR
    public List<Alumno> mostrarAlumnos() {
        return alumnoRepository.findAll();
    }

    public Alumno sumarAlumno(Alumno alumno) {
       // Response respuesta = new Response();
        //List error = validarAlumno(alumno);

        //if (error.size() == 0) {
            alumnoRepository.save(alumno);
       //     respuesta.setData(Collections.singletonList(alumno));
        //} else {
        //    respuesta.setMensajeError(error);
       // }

        return alumno;
    }

    public Alumno editarAlumno(Long id, Alumno alumno) {

        if (Objects.isNull(id) || !alumnoRepository.existsById(id)) {
            throw new IdInvalidoException(id);
        }

        borrarAlumno(id);
        sumarAlumno(alumno);
        //retorna el alumno nuevo
        return alumno;
    }

    public List<Alumno> borrarAlumno(Long id) {

        if (Objects.isNull(id) || !alumnoRepository.existsById(id)) {
            throw new IdInvalidoException(id);
        } else {
            alumnoRepository.deleteById(id);
        }

        //retorna lista de los que quedaron
        return alumnoRepository.findAll();
    }

    private List<String> validarAlumno(Alumno alumno) {
        List errores = new ArrayList<String>();

        //if (Objects.isNull(alumno.getApellido()) ||
        //        Objects.isNull(alumno.getNombre()) ||
        /*        Objects.isNull(alumno.isAbonoMatricula()) ||
                Objects.isNull(alumno.getEdad()) ||
                Objects.isNull(alumno.getNotaExamenIngreso()) ||
                Objects.isNull(alumno.isAdeudaMateriasSecundario()))
            errores.add("Ningún campo debe estar vacío.");*/

        //if (alumno.getEdad() < 0 || alumno.getEdad() > 100)
        //    errores.add("La edad no puede ser numero negativo ni mayor a 100.");
        //if (alumno.getApellido().length() > 20 || alumno.getNombre().length() > 20)
        //    errores.add("Ni el nombre ni el apellido debe exceder los 20 carácteres");
       // if (!validarLetrasYEspacios(alumno.getApellido()) || !validarLetrasYEspacios(alumno.getNombre()))
         //   errores.add("El nombre y el apellido deben contener solo letras mayúsculas, minúsculas y espacios.");
       // if (alumno.getNotaExamenIngreso() < 1 || alumno.getNotaExamenIngreso() > 10)
        // errores.add("La nota debe ir del 1 al 10");

        return errores;
    }

    public boolean validarLetrasYEspacios(String campo) {
        String regex = "^[a-zA-Z ]+$";
        return campo.matches(regex);
    }

    public List<Alumno> ordenarDecrecientementePorApellido() {
        return alumnoRepository.findAll().stream().sorted(Comparator.comparing((Alumno a) -> a.getApellido()).reversed()).toList();
    }

    public double obtenerPromedioEdades() {
        //implemento lo mismo pero llamo al repo y uso findAll()
        double totalEdades = alumnoRepository.findAll().stream().mapToInt(Alumno::getEdad).sum();
        return totalEdades / alumnoRepository.findAll().size();
    }

    public List<Alumno> obtenerAlumnosQueAdeudanMaterias() {
        return alumnoRepository.findAll().stream().filter(a -> a.isAdeudaMateriasSecundario() == true).toList();
    }

    public List<Alumno> obtenerAlumnosQueNoAbonaron() {
        return alumnoRepository.findAll().stream().filter(a -> a.isAbonoMatricula() == false).toList();
    }

    public List<Alumno> obtenerAlumnoConMayorNota() {
        double notaMaxima = alumnoRepository.findAll().stream().mapToDouble(Alumno::getNotaExamenIngreso).max().orElse(0);
        return alumnoRepository.findAll().stream().filter(a -> a.getNotaExamenIngreso() == notaMaxima).collect(Collectors.toList());
    }

}
