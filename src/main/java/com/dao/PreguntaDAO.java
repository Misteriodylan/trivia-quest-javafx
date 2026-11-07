package com.dao;

import com.model.Pregunta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreguntaDAO {
    // Configura tus credenciales de conexión aquí
    private static final String URL = "jdbc:mysql://localhost:3306/trivia_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "usbw";

    // Nombres de las tablas independientes en la base de datos
    private final String[] tablasCategorias = {
        "peliculas", 
        "series", 
        "animes", 
        "futbol_argentino", 
        "futbol_europeo", 
        "dibujos_animados"
    };

    /**
     * Obtiene todas las preguntas de las 6 tablas de categorías de la base de datos.
     */
    public List<Pregunta> obtenerTodas() {
        List<Pregunta> listaPreguntas = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD)) {
            for (String tabla : tablasCategorias) {
                String query = "SELECT * FROM " + tabla;
                try (Statement stmt = conexion.createStatement();
                     ResultSet rs = stmt.executeQuery(query)) {

                    while (rs.next()) {
                        String enunciado = rs.getString("enunciado");
                        String tipo = rs.getString("tipo");
                        int respuestaCorrecta = rs.getInt("respuesta_correcta");

                        String opA = rs.getString("opcion_a");
                        String opB = rs.getString("opcion_b");
                        String opC = rs.getString("opcion_c");
                        String opD = rs.getString("opcion_d");

                        String[] opciones;
                        if ("VERDADERO_FALSO".equals(tipo) || opC == null) {
                            opciones = new String[]{opA, opB};
                        } else {
                            opciones = new String[]{opA, opB, opC, opD};
                        }

                        // Mapear el nombre de la tabla a formato de categoría legible para la UI
                        String categoriaLegible = formatearNombreCategoria(tabla);

                        // Creamos la instancia de la Pregunta con los datos de la BD
                        Pregunta p = new Pregunta(enunciado, opciones, respuestaCorrecta, categoriaLegible);
                        listaPreguntas.add(p);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar o consultar la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return listaPreguntas;
    }
public List<Pregunta> obtenerPorCategoria(String categoriaBuscada) {
    List<Pregunta> todas = obtenerTodas();
    List<Pregunta> filtradas = new ArrayList<>();

    for (Pregunta p : todas) {
        // Compara ignorando mayúsculas/minúsculas
        if (p.getCategoria().equalsIgnoreCase(categoriaBuscada) ||
            formatearSinTildes(p.getCategoria()).equalsIgnoreCase(formatearSinTildes(categoriaBuscada))) {
            filtradas.add(p);
        }
    }
    return filtradas;
}

private String formatearSinTildes(String texto) {
    if (texto == null) return "";
    return texto.replace("á", "a").replace("é", "e").replace("í", "i")
                .replace("ó", "o").replace("ú", "u")
                .replace("Á", "A").replace("É", "E").replace("Í", "I")
                .replace("Ó", "O").replace("Ú", "U");
}
    // Convierte el nombre de la tabla de la BD en un nombre de categoría con formato prolijo
    private String formatearNombreCategoria(String tabla) {
        switch (tabla) {
            case "peliculas": return "Películas";
            case "series": return "Series";
            case "animes": return "Animes";
            case "futbol_argentino": return "Fútbol argentino";
            case "futbol_europeo": return "Fútbol europeo";
            case "dibujos_animados": return "Dibujos animados";
            default: return "General";
        }
    }
}