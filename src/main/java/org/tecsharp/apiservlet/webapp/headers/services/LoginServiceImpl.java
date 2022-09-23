package org.tecsharp.apiservlet.webapp.headers.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.tecsharp.apiservlet.webapp.headers.repositories.UsuarioRepository;
import org.tecsharp.apiservlet.webapp.headers.repositories.UsuarioRepositoryImpl;

import java.util.Optional;

public class LoginServiceImpl implements LoginService{
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
}
