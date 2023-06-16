package com.example.alumnosApi.controllers;

import com.example.alumnosApi.exceptions.IdInvalidoException;
import com.example.alumnosApi.services.RespuestaError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import java.util.List;
import java.util.StringJoiner;
import org.springframework.validation.FieldError;


@RestControllerAdvice
public class ExceptionHandlerGlobal {

    @ExceptionHandler(NoHandlerFoundException.class)
    public RespuestaError handleNoHandlerFoundException(NoHandlerFoundException ex) {
        RespuestaError respuestaError = new RespuestaError();
        respuestaError.setCodigo(HttpStatus.NOT_FOUND.value());
        respuestaError.setMensaje("La ruta a la que ingresó no existe.");

        return respuestaError;
    }
}
