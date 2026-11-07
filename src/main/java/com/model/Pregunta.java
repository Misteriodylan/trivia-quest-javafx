package com.model;

import com.interfaz.Evaluable;

public class Pregunta implements Evaluable {
    private String enunciado;
    private String[] opciones;
    private int respuestaCorrecta;
    private String categoria;

    public Pregunta(String enunciado, String[] opciones, int respuestaCorrecta, String categoria) {
        this.enunciado = enunciado;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
        this.categoria = categoria;
    }

    public String getEnunciado() { return enunciado; }
    public String[] getOpciones() { return opciones; }
    public int getRespuestaCorrecta() { return respuestaCorrecta; }
    public String getCategoria() { return categoria; }

    @Override
    public boolean evaluarRespuesta(int opcionSeleccionada) {
        return this.respuestaCorrecta == opcionSeleccionada;
    }
}