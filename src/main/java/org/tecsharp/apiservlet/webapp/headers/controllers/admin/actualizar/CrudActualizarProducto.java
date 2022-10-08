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
import org.tecsharp.apiservlet.webapp.headers.services.crud.CrudService;
import org.tecsharp.apiservlet.webapp.headers.services.crud.impl.CrudServiceImpl;
import org.tecsharp.apiservlet.webapp.headers.services.login.LoginService;
import org.tecsharp.apiservlet.webapp.headers.services.login.impl.LoginServiceSessionImpl;
import org.tecsharp.apiservlet.webapp.headers.services.producto.ProductoService;
import org.tecsharp.apiservlet.webapp.headers.services.producto.impl.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/crud/actualizar/producto")
public class CrudActualizarProducto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //SE OBTIENE EL USUARIO
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);
        req.setAttribute("username", usernameOptional);

        //SE OBTIENE EL OBJETO USUARIO
        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario"); //SE RECUPERA EL USUARIO

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


        if (usernameOptional.isPresent() && usuario.getUserType() == 2) {
            if (producto != null) {
                //ENVIA PRODUCTO ELIMINADO
                getServletContext().getRequestDispatcher("/crud-actualizar-producto.jsp").forward(req, resp);
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/crud/actualizar");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer categoria = Integer.valueOf(req.getParameter("categoria"));
        String nombre = req.getParameter("nombre");
        Integer precio = Integer.valueOf(req.getParameter("precio"));
        Integer stock = Integer.valueOf(req.getParameter("stock"));
        String shortdescription = req.getParameter("shortdescription");
        String largedescription = req.getParameter("largedescription");
        Integer status = Integer.valueOf(req.getParameter("status"));
        Integer idProducto = Integer.valueOf(req.getParameter("idProducto"));

        //SERVICE
        //SE OBTIENE EL USUARIO, SU ID Y SU TIPO USUARIO
        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario"); //SE RECUPERA EL USUARIO
        Integer userId = usuario.getIdUser(); //SE OBTIENE EL USER ID
        Integer userType = usuario.getUserType();

        CrudService crudService = new CrudServiceImpl();
        if(usuario.getUserType() == 2 && crudService.actualizarProducto(userId, userType, categoria, nombre, precio, stock, shortdescription, largedescription, status, idProducto)){
            getServletContext().getRequestDispatcher("/crud/actualizar/valid.html").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/crud/actualizar");
        }
    }
}
