package org.tecsharp.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.tecsharp.apiservlet.webapp.headers.services.producto.ProductoService;
import org.tecsharp.apiservlet.webapp.headers.services.producto.impl.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/agregar/carro")
public class ProductoAgregarCarroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer productoID = Integer.valueOf(req.getParameter("productoID"));
        Integer idUser = Integer.valueOf(req.getParameter("idUser"));

        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        service.agregarProductoAlCarrito(productoID, idUser);

//        List<Producto> proca = service.getCarrito(2);
//        req.getSession().setAttribute("proca", proca);
//        req.setAttribute("proca", proca);

        resp.sendRedirect(req.getContextPath() + "/ver/carrito");
        //getServletContext().getRequestDispatcher("/ver/carrito").forward(req, resp);

    }

}
