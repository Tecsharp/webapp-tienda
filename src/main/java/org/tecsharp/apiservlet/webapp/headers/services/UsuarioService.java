package org.tecsharp.apiservlet.webapp.headers.services;

import org.tecsharp.apiservlet.webapp.headers.models.Usuario;

public interface UsuarioService {

    Usuario login(String username, String password);

}
