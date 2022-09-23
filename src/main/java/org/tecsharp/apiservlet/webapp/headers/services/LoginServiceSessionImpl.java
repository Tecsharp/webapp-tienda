package org.tecsharp.apiservlet.webapp.headers.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LoginServiceSessionImpl implements LoginService{
    @Override
    public Optional<String> getUsername(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {
            return Optional.of(username);
        }
        return Optional.empty();
    }

    public Optional<Integer> getId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer idUser = (Integer) session.getAttribute("idUser");
        if (idUser != null) {
            return Optional.of(idUser);
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> getUser(HttpServletRequest req) {
        return Optional.empty();
    }
}
