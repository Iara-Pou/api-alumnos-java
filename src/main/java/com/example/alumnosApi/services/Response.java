package com.example.alumnosApi.services;

import com.example.alumnosApi.entities.Alumno;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class Response {
    private List<Alumno> data;
    private List<String> mensajeError;
}
