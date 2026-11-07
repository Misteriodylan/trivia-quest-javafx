package com.excepciones;

public class TiempoAgotadoException extends Exception {
    public TiempoAgotadoException(String mensaje) {
        super(mensaje);
    }
}