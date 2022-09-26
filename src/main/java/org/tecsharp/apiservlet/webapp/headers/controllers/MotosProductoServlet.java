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

@WebServlet({"/productos/motocicletas"})
public class MotosProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn" );

        //Productos de motocicleta
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        Integer productoTipo = 1; //AGREGAR UN LINK QUE MANDE ESTE ITEM
        List<Producto> tipoProductos = service.listarByTipo(productoTipo);

        //Lista de categoria
        List<TipoProducto> categorias = service.listarTipoProducto();



        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req); //SE PREPARA

        req.setAttribute("categorias", categorias); //se envia a la vista
        req.setAttribute("productos", tipoProductos); //se envia a la vista
        req.setAttribute("username", usernameOptional); //SE ENVIA A LA VISTA
        getServletContext().getRequestDispatcher("/motocicletas.jsp").forward(req, resp);
    }
}
