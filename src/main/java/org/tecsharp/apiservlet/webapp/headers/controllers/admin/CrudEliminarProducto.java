package org.tecsharp.apiservlet.webapp.headers.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.models.TipoProducto;
import org.tecsharp.apiservlet.webapp.headers.services.producto.ProductoService;
import org.tecsharp.apiservlet.webapp.headers.services.producto.impl.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/crud/eliminar/producto")
public class CrudEliminarProducto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        Integer idProducto = Integer.valueOf(req.getParameter("id"));
        ProductoService service = new ProductoServiceJdbcImpl(conn);

        //SE RECUPERA LA ID DE LA CATEGORIA
        Integer idTipo = null;
        //Integer idTipo = Integer.valueOf(req.getParameter("idTipo"));
        TipoProducto tipoProducto = new TipoProducto();
        tipoProducto.setId(idTipo);

        Producto producto = service.obtenerProductoPorId(idProducto, tipoProducto);
        req.setAttribute("producto", producto);



        if(service.eliminarProductoPorId(idProducto)){

            //ENVIA PRODUCTO ELIMINADO
            getServletContext().getRequestDispatcher("/crud/eliminar/valid.html").forward(req, resp);

        } else {
            resp.sendRedirect(req.getContextPath() + "/inicio");
        }



    }
}
