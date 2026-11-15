package com.excepciones;

public class TiempoAgotadoException extends RuntimeException {

    public TiempoAgotadoException(String mensaje) {
        super(mensaje);
    }
}
