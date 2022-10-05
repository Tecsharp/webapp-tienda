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
import java.util.List;

@WebServlet("/crud/actualizar/producto")
public class CrudActualizarProducto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        Integer idProducto = Integer.valueOf(req.getParameter("id"));
        ProductoService serviceProducto = new ProductoServiceJdbcImpl(conn);

        //SE RECUPERA LA ID DE LA CATEGORIA
        Integer idTipo = Integer.valueOf(req.getParameter("idTipo"));
        //Integer idTipo = null;
        TipoProducto tipo = new TipoProducto();
        tipo.setId(idTipo);
        //SE ENVIA EL PRODUCTO SELECCIONADO EN VISTA
        Producto producto = serviceProducto.obtenerProductoPorId(idProducto, tipo);
        req.setAttribute("producto", producto);


        //SE ENVIA LISTA DE CATEGORIAS
        List<TipoProducto> categorias = serviceProducto.listarTipoProducto();
        req.setAttribute("categorias", categorias); //SE ENVIA A LA VISTA



        if(producto != null){

            //ENVIA PRODUCTO ELIMINADO
            getServletContext().getRequestDispatcher("/crud-actualizar-producto.jsp").forward(req, resp);

        } else {
            resp.sendRedirect(req.getContextPath() + "/crud/actualizar");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




    }
}
