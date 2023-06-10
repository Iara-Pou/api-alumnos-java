package com.example.alumnosApi.services;

import com.example.alumnosApi.entities.Alumno;
import com.example.alumnosApi.entities.Curso;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CursoServices {

    Curso curso = new Curso();
    List<Alumno> listaAlumnos = curso.getAlumnos();

    public List mostrarAlumnos() {
        return ordenarDecrecientementePorApellido(this.curso);
        //falta ordenar por coso descendiente
    }

    public String sumarAlumno(Alumno alumno) {
        if (validarAlumno(alumno)) {
            listaAlumnos.add(alumno);
            return "¡El usuario " + alumno.getNombre() + " " + alumno.getApellido() + " se ha ingresado correctamente!";
        } else {
            return "La operación falló. Revisar los datos ingresados, ¡Por favor!";
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

    private boolean validarAlumno (Alumno alumno) {
        boolean UsuarioValido = true;

        UsuarioValido = alumno.getEdad() == 0 ? false : true;
        UsuarioValido = alumno.getApellido() == null ? false : true;
        UsuarioValido = alumno.getNombre() == null ? false : true;

        //esto funciona mal porque con Gson, rellena los nulos con false, y los ids con 0.
        //La edad tampoco funciona, wtf
        //     UsuarioValido = alumno.isAbonoMatricula() == null ? false: true;
        //    UsuarioValido = alumno.getNombre() == null ? false: true;

        return UsuarioValido;
    }

    public List<Alumno> ordenarDecrecientementePorApellido(Curso curso){
        return listaAlumnos.stream().sorted(Comparator.comparing((Alumno a) -> a.getApellido()).reversed()).toList();
    }

    public double obtenerPromedioEdades(){
        double totalEdades = 0;

        for(int i = 0; i<listaAlumnos.size(); i++){
            totalEdades += listaAlumnos.get(i).getEdad();
        }

        return totalEdades / (listaAlumnos.size());
    }


    public List<Alumno> obtenerAlumnosQueAdeudanMaterias(){
        return listaAlumnos.stream().filter(a -> a.isAdeudaMateriasSecundario() == true).toList();
    }

    public List<Alumno> obtenerAlumnosQueNoAbonaron(){
        return listaAlumnos.stream().filter(a -> a.isAbonoMatricula() == false).toList();
    }

    public List<Alumno> obtenerAlumnoConMayorNota(){
        double notaMaxima = 0;

        for (int i = 0; i<this.listaAlumnos.size(); i++){
            double notaIterada = listaAlumnos.get(i).getNotaExamenIngreso();
            if (notaIterada > notaMaxima) {
                notaMaxima = notaIterada;
            }
        }
        //puede haber + de uno con mayor nota. Por eso lista
        double finalNotaMaxima = notaMaxima;
        return listaAlumnos.stream().filter(a -> a.getNotaExamenIngreso() == finalNotaMaxima).toList();
    }

}
