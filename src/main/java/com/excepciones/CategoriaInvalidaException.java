package com.excepciones;

public class CategoriaInvalidaException extends Exception {
    public CategoriaInvalidaException(String mensaje) {
        super(mensaje);
    }
}