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

    //@ExceptionHandler(Exception.class)
    public RespuestaError handleException(Exception ex) {

        RespuestaError respuestaError = new RespuestaError();
        respuestaError.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        respuestaError.setMensaje("Ocurrió un error en el servidor. Por favor, intenta de nuevo más tarde.");

        return respuestaError;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RespuestaError handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        RespuestaError respuestaError = new RespuestaError();

        respuestaError.setCodigo(HttpStatus.BAD_REQUEST.value());

        List<FieldError> inputsErroneos = ex.getBindingResult().getFieldErrors();
        StringJoiner errores = new StringJoiner(", ");
        for (FieldError error : inputsErroneos) {
            String nombreInput = error.getField();
            String errorInput = nombreInput +": " + error.getDefaultMessage();
            errores.add(errorInput);
        }
        respuestaError.setMensaje(errores.toString());

        return respuestaError;
    }

    @ExceptionHandler(IdInvalidoException.class)
    public RespuestaError handleIdInvalidoException(IdInvalidoException ex) {
        RespuestaError respuestaError = new RespuestaError();
        respuestaError.setCodigo(HttpStatus.BAD_REQUEST.value());
        respuestaError.setMensaje(ex.getMessage());

        return respuestaError;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public RespuestaError handleNoHandlerFoundException(NoHandlerFoundException ex) {
        RespuestaError respuestaError = new RespuestaError();
        respuestaError.setCodigo(HttpStatus.NOT_FOUND.value());
        respuestaError.setMensaje("La ruta a la que ingresó no existe.");

        return respuestaError;
    }
}
