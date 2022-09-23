package org.tecsharp.apiservlet.webapp.headers.repositories;

import org.tecsharp.apiservlet.webapp.headers.models.Usuario;

public interface UsuarioRepository {

    Usuario findByUsernameAndPassword(String username, String password);

}
