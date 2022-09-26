package org.tecsharp.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.models.TipoProducto;
import org.tecsharp.apiservlet.webapp.headers.services.login.LoginService;
import org.tecsharp.apiservlet.webapp.headers.services.login.impl.LoginServiceSessionImpl;
import org.tecsharp.apiservlet.webapp.headers.services.producto.ProductoService;
import org.tecsharp.apiservlet.webapp.headers.services.producto.impl.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/ver/producto")
public class VerProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);

        //OBJETO PRODUCTO
        Producto producto = service.obtenerProductoPorId(id);
        req.setAttribute("producto", producto); //SE ENVIA A LA VISTA



        //LISTA DE CATEGORIAS
        List<TipoProducto> categorias = service.listarTipoProducto();
        req.setAttribute("categorias", categorias); //SE ENVIA A LA VISTA

        //USUARIO DISPONIBLE
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);
        req.setAttribute("username", usernameOptional);

        getServletContext().getRequestDispatcher("/product_details.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        resp.sendRedirect(req.getContextPath() + "/ver/producto");
    }
}
