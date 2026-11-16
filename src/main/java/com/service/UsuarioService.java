
package com.service;

import com.dao.UsuarioDAO;
import java.util.List;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    // Registrar un usuario
    public boolean registrarUsuario(String nombreUsuario, String password) {
        return usuarioDAO.registrarUsuario(nombreUsuario, password);
    }

    // Validar el inicio de sesión
    public boolean validarLogin(String nombreUsuario, String password) {
        return usuarioDAO.validarLogin(nombreUsuario, password);
    }

    // Guardar el puntaje del usuario
    public void guardarPuntaje(String nombreUsuario, int puntaje) {
        usuarioDAO.guardarPuntaje(nombreUsuario, puntaje);
    }

    // Obtener los mejores 10 puntajes
    public List<String[]> obtenerTopRanking() {
        return usuarioDAO.obtenerTopRanking();
    }
}
