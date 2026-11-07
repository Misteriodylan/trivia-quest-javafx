package com.excepciones;

public class SinVidasDisponiblesException extends Exception {
    public SinVidasDisponiblesException(String mensaje) {
        super(mensaje);
    }
}