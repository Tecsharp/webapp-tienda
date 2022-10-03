package org.tecsharp.apiservlet.webapp.headers.services.login;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface LoginService {
    Optional<String> getUsername(HttpServletRequest request);
    Optional<Integer> getId(HttpServletRequest request);
    Optional<String> getUser(HttpServletRequest request);
    Optional<Integer> getUserType(HttpServletRequest request);


}
