package org.tecsharp.apiservlet.webapp.headers.controllers.admin.actualizar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.models.TipoProducto;
import org.tecsharp.apiservlet.webapp.headers.models.Usuario;
import org.tecsharp.apiservlet.webapp.headers.services.login.LoginService;
import org.tecsharp.apiservlet.webapp.headers.services.login.impl.LoginServiceSessionImpl;
import org.tecsharp.apiservlet.webapp.headers.services.producto.ProductoService;
import org.tecsharp.apiservlet.webapp.headers.services.producto.impl.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/crud/actualizar/valid.html")
public class CrudActualizarValidacion extends HttpServlet {


    Integer idProducto;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //SE OBTIENE EL USUARIO
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);
        req.setAttribute("username", usernameOptional);


        Connection conn = (Connection) req.getAttribute("conn");

        ProductoService service = new ProductoServiceJdbcImpl(conn);

        //SE RECUPERA LA ID DE LA CATEGORIA
        Integer idTipo = null;
        //Integer idTipo = Integer.valueOf(req.getParameter("idTipo"));
        TipoProducto tipoProducto = new TipoProducto();
        tipoProducto.setId(idTipo);

        Producto producto = service.obtenerProductoPorId(idProducto, tipoProducto);
        req.setAttribute("producto", producto);

        //SE OBTIENE EL OBJETO USUARIO
        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario"); //SE RECUPERA EL USUARIO


        if (usernameOptional.isPresent() && usuario.getUserType() == 2) {
            getServletContext().getRequestDispatcher("/crud-actualizado.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        idProducto = Integer.valueOf(req.getParameter("idProducto")); //SE OBTIENE EL ID DEL PRODUCTO DE SERVLET /crud/actualizar/prducto
        resp.sendRedirect(req.getContextPath() + "/crud/actualizar/valid.html");

    }
}
