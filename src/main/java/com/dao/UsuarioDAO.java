package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/trivia_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "usbw";

    // Registrar un nuevo usuario
    public boolean registrarUsuario(String nombreUsuario, String password) {
        String query = "INSERT INTO usuarios (nombre_usuario, password) VALUES (?, ?)";
        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            
            pstmt.setString(1, nombreUsuario);
            pstmt.setString(2, password); // Nota: idealmente encriptado, pero en texto plano funciona para el proyecto escolar
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al registrar usuario (quizás ya existe): " + e.getMessage());
            return false;
        }
    }

    // Validar inicio de sesión
    public boolean validarLogin(String nombreUsuario, String password) {
        String query = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND password = ?";
        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            
            pstmt.setString(1, nombreUsuario);
            pstmt.setString(2, password);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // True si encuentra coincidencia
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al validar login: " + e.getMessage());
            return false;
        }
    }

    // Guardar puntaje en el ranking
    public void guardarPuntaje(String nombreUsuario, int puntaje) {
        // Primero buscamos el ID del usuario
        String queryId = "SELECT id FROM usuarios WHERE nombre_usuario = ?";
        String queryInsertRanking = "INSERT INTO ranking (id_usuario, puntaje) VALUES (?, ?)";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmtId = conexion.prepareStatement(queryId)) {
            
            pstmtId.setString(1, nombreUsuario);
            try (ResultSet rs = pstmtId.executeQuery()) {
                if (rs.next()) {
                    int idUsuario = rs.getInt("id");
                    
                    try (PreparedStatement pstmtInsert = conexion.prepareStatement(queryInsertRanking)) {
                        pstmtInsert.setInt(1, idUsuario);
                        pstmtInsert.setInt(2, puntaje);
                        pstmtInsert.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al guardar en el ranking: " + e.getMessage());
        }
    }

    // Obtener el Top 10 del Ranking para mostrar en la interfaz
    public List<String[]> obtenerTopRanking() {
        List<String[]> topList = new ArrayList<>();
        String query = "SELECT u.nombre_usuario, r.puntaje FROM ranking r " +
                       "JOIN usuarios u ON r.id_usuario = u.id " +
                       "ORDER BY r.puntaje DESC LIMIT 10";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String nombre = rs.getString("nombre_usuario");
                String puntaje = String.valueOf(rs.getInt("puntaje"));
                topList.add(new String[]{nombre, puntaje});
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener el ranking: " + e.getMessage());
        }
        return topList;
    }
}