package org.tecsharp.apiservlet.webapp.headers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.tecsharp.apiservlet.webapp.headers.services.carrito.CarritoService;
import org.tecsharp.apiservlet.webapp.headers.services.carrito.impl.CarritoServiceImpl;

import java.io.IOException;

@WebServlet("/eliminar/producto")
public class EliminarProductoCarritoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer idProducto = Integer.valueOf(req.getParameter("idProducto"));
        Integer userId = Integer.valueOf(req.getParameter("userId"));
        try {
            CarritoService carritoService = new CarritoServiceImpl();
            carritoService.eliminarPorductoDeCarrito(idProducto, userId);
        } catch (Exception e){

        }


        resp.sendRedirect(req.getContextPath() + "/ver/carrito");

    }
}
