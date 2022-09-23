package org.tecsharp.apiservlet.webapp.headers.services;

import org.tecsharp.apiservlet.webapp.headers.models.Usuario;
import org.tecsharp.apiservlet.webapp.headers.repositories.UsuarioRepositoryImpl;

public class UsuarioServiceImpl implements UsuarioService {
    @Override
    public Usuario login(String username, String password) {
        UsuarioRepositoryImpl repository = new UsuarioRepositoryImpl();

        Usuario usuario = repository.findByUsernameAndPassword(username, password);
        if (!islogged(usuario)) {
            return usuario;
        }

//        if (!isActive(usuario)) {
//            return null;
//        }

        return usuario;
    }

    private boolean islogged(Usuario usuario) {

        if (usuario != null) {
            return true;
        } else {
            System.out.println("No se encontro el usuario");
            return false;
        }

    }


}
