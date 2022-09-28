package org.tecsharp.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.tecsharp.apiservlet.webapp.headers.services.carrito.CarritoService;
import org.tecsharp.apiservlet.webapp.headers.services.carrito.impl.CarritoServiceImpl;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/agregar/carro")
public class AgregarProductoCarroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //SE OBTIENE EL PRODUCTO A AGREGAR Y EL ID DE USUARIO
        Integer productoID = Integer.valueOf(req.getParameter("productoID"));
        Integer idUser = Integer.valueOf(req.getParameter("idUser"));
        Integer numItems = 1;

        //CONEXION BDD
        Connection conn = (Connection) req.getAttribute("conn");

        //IMPLEMENT SERVICE
        CarritoService service = new CarritoServiceImpl();
        service.agregarProductoAlCarrito(productoID, idUser, numItems);

        resp.sendRedirect(req.getContextPath() + "/ver/carrito");
    }

}
