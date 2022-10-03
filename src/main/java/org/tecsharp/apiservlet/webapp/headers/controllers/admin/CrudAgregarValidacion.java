package org.tecsharp.apiservlet.webapp.headers.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.tecsharp.apiservlet.webapp.headers.models.Usuario;
import org.tecsharp.apiservlet.webapp.headers.services.login.LoginService;
import org.tecsharp.apiservlet.webapp.headers.services.login.impl.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/crud/agregar/valid.html")
public class CrudAgregarValidacion extends HttpServlet {

    String nombre;
    Integer categoria;
    Integer precio;
    Integer stock;
    String shortDescription;
    String largeDescription;
    Integer status;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //SE OBTIENE EL USUARIO
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        req.setAttribute("nombre", nombre);
        req.setAttribute("categoria", categoria);
        req.setAttribute("precio", precio);
        req.setAttribute("stock", stock);
        req.setAttribute("shortDescription", shortDescription);
        req.setAttribute("largeDescription", largeDescription);
        req.setAttribute("status", status);

        //OBTENER USERTYPE (ADMIN)
        try {
            HttpSession session = req.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuario"); //SE RECUPERA EL USUARIO
            Integer userId = usuario.getIdUser(); //SE OBTIENE EL USER ID
            Integer userType = usuario.getUserType();

        } catch (Exception e) {

        }

        if (usernameOptional.isPresent()) {
            getServletContext().getRequestDispatcher("/crud-agregado.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        nombre = req.getParameter("nombre");
        categoria = Integer.valueOf(req.getParameter("categoria"));
        nombre = req.getParameter("nombre");
        precio = Integer.valueOf(req.getParameter("precio"));
        stock = Integer.valueOf(req.getParameter("stock"));
        shortDescription = req.getParameter("shortdescription");
        largeDescription = req.getParameter("largedescription");
        status = Integer.valueOf(req.getParameter("status"));

        resp.sendRedirect(req.getContextPath() + "/crud/agregar/valid.html");

    }
}
