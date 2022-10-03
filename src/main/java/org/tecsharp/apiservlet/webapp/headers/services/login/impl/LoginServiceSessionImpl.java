package org.tecsharp.apiservlet.webapp.headers.services.login.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.tecsharp.apiservlet.webapp.headers.models.Usuario;
import org.tecsharp.apiservlet.webapp.headers.services.login.LoginService;

import javax.swing.text.html.Option;
import java.util.Optional;

public class LoginServiceSessionImpl implements LoginService {
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

    public Optional<Integer> getUserType(HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer userType = (Integer) session.getAttribute("userType");
        if(userType != null){
            return Optional.of(userType);
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> getUser(HttpServletRequest request) {
        return Optional.empty();
    }


}
