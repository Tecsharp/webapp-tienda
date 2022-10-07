package org.tecsharp.apiservlet.webapp.headers.repositories.usuario;

import org.tecsharp.apiservlet.webapp.headers.models.Usuario;

public interface UsuarioRepository {

    Usuario findByUsernameAndPassword(String username, String password);
    Integer obtenerNumeroDeUsuariosRegistrados();
    boolean registrarUsuario(String nombre, String apellido, String email, String username, String password);
}
