package org.tecsharp.apiservlet.webapp.headers.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.tecsharp.apiservlet.webapp.headers.services.login.LoginService;
import org.tecsharp.apiservlet.webapp.headers.services.login.impl.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

@WebFilter({"/ver/carrito"})
public class LoginFiltro implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        LoginService service = new LoginServiceSessionImpl();
        Optional<String> username = service.getUsername((HttpServletRequest) request);
        if(username.isPresent()){
            chain.doFilter(request, response);

        } else{

            //((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos, no estas autorizado para ingresar a esta pagina");
            ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/404");
        }
    }
}
