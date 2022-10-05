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

@WebServlet("/crud/actualizar/valid.html")
public class CrudActualizarValidacion extends HttpServlet {

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
            getServletContext().getRequestDispatcher("/crud-actualizado.jsp").forward(req, resp);
        }
    }
}
