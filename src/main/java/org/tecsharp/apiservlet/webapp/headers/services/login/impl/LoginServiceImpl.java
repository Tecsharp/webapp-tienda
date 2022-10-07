package org.tecsharp.apiservlet.webapp.headers.services.login.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.tecsharp.apiservlet.webapp.headers.repositories.crud.CrudRepository;
import org.tecsharp.apiservlet.webapp.headers.repositories.usuario.UsuarioRepository;
import org.tecsharp.apiservlet.webapp.headers.repositories.usuario.UsuarioRepositoryImpl;
import org.tecsharp.apiservlet.webapp.headers.services.login.LoginService;

import java.util.Optional;

public class LoginServiceImpl implements LoginService {
    @Override
    public Optional<String> getUsername(HttpServletRequest req) {
        return Optional.empty();
    }

    @Override
    public Optional<Integer> getId(HttpServletRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<String> getUser(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        UsuarioRepository user = new UsuarioRepositoryImpl();

        if (user.findByUsernameAndPassword(username, password) != null){
            return Optional.of(username);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Integer> getUserType(HttpServletRequest request) {
        return Optional.empty();
    }

    @Override
    public boolean registrarUsuario(String name, String apellido, String email, String username, String password) {
        UsuarioRepository usuarioRepository = new UsuarioRepositoryImpl();
        return usuarioRepository.registrarUsuario(name, apellido, email, username, password);
    }
}
