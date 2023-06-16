package com.example.alumnosApi.exceptions;

public class IdInvalidoException extends RuntimeException{
        public IdInvalidoException(Long id) {
            super("El usuario número " + id +" no se encuentra en los registros.");
    }
}
