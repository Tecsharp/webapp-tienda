package org.tecsharp.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.tecsharp.apiservlet.webapp.headers.models.Carro;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.models.TipoProducto;
import org.tecsharp.apiservlet.webapp.headers.models.Usuario;
import org.tecsharp.apiservlet.webapp.headers.services.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet({"/inicio"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);


        Connection conn = (Connection) req.getAttribute("conn" );
        ProductoService serviceTipoProducto = new ProductoServiceJdbcImpl(conn);

        //SERVICIO LISTA TIPO PRODUCTOS
        List<TipoProducto> categorias = serviceTipoProducto.listarTipoProducto();
        req.setAttribute("categorias", categorias); //SE ENVIA A LA VISTA

        //SERVICIO LISTA 4 PRODUCTO NUEVOS PRODUCTOS

        List<Producto> carruselUno = serviceTipoProducto.listarCarrusel(1);
        req.setAttribute("carruselUno", carruselUno); //SE ENVIA A LA VISTA

        //SERVICIO LISTA 4 PRODUCTO NUEVOS PRODUCTOS
        List<Producto> carruselDos = serviceTipoProducto.listarCarrusel(2);
        req.setAttribute("carruselDos", carruselDos); //SE ENVIA A LA VISTA
        req.setAttribute("username", usernameOptional);


        if (usernameOptional.isPresent()) {

            getServletContext().getRequestDispatcher("/index.html").forward(req, resp);

        } else {
            getServletContext().getRequestDispatcher("/inicio.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //SERVICIO USUARIO
        UsuarioService service = new UsuarioServiceImpl();
        Usuario usuario = service.login(username, password);



        if (usuario != null) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            req.setAttribute("usuario", usuario); // add to request
            req.getSession().setAttribute("usuario", usuario); // add to session
            resp.sendRedirect(req.getContextPath() + "/inicio");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado para ingresar a esta página!");
        }
    }
}
