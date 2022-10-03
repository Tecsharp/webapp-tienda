package org.tecsharp.apiservlet.webapp.headers.services.login.impl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.tecsharp.apiservlet.webapp.headers.services.login.LoginService;

import java.util.Arrays;
import java.util.Optional;

public class LoginServiceCookieImpl implements LoginService {
    @Override
    public Optional<String> getUsername(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies() != null ? req.getCookies(): new Cookie[0];
        return Arrays.stream(cookies)
                .filter(c-> "username".equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
    }

    @Override
    public Optional<Integer> getId(HttpServletRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<String> getUser(HttpServletRequest req) {
        return Optional.empty();
    }

    @Override
    public Optional<Integer> getUserType(HttpServletRequest request) {
        return Optional.empty();
    }
}
