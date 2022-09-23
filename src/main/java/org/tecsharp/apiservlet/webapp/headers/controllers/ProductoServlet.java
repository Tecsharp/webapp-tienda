package org.tecsharp.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;

import org.tecsharp.apiservlet.webapp.headers.services.LoginService;
import org.tecsharp.apiservlet.webapp.headers.services.LoginServiceSessionImpl;
import org.tecsharp.apiservlet.webapp.headers.services.ProductoService;
import org.tecsharp.apiservlet.webapp.headers.services.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn" );
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        Integer productoTipo = 1;
        List<Producto> productos = service.listar();


        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req); //SE PREPARA

        req.setAttribute("productos", productos); //se envia a la vista
        req.setAttribute("username", usernameOptional); //SE ENVIA A LA VISTA
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
    }
}
