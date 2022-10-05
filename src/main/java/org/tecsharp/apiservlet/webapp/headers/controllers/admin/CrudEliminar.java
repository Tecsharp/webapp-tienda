package org.tecsharp.apiservlet.webapp.headers.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.tecsharp.apiservlet.webapp.headers.models.Carrito;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.models.TipoProducto;
import org.tecsharp.apiservlet.webapp.headers.models.Usuario;
import org.tecsharp.apiservlet.webapp.headers.services.carrito.CarritoService;
import org.tecsharp.apiservlet.webapp.headers.services.carrito.impl.CarritoServiceImpl;
import org.tecsharp.apiservlet.webapp.headers.services.login.LoginService;
import org.tecsharp.apiservlet.webapp.headers.services.login.impl.LoginServiceSessionImpl;
import org.tecsharp.apiservlet.webapp.headers.services.producto.ProductoService;
import org.tecsharp.apiservlet.webapp.headers.services.producto.impl.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@WebServlet("/crud/eliminar")
public class CrudEliminar extends HttpServlet {

    Integer categoria = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //CONEXION BDD
        Connection conn = (Connection) req.getAttribute("conn");

        //IMPLEMENT SERVICE
        ProductoService service = new ProductoServiceJdbcImpl(conn);

        //SE RECUPERA LA ID DE LA CATEGORIA
        //Integer idTipo = Integer.valueOf(req.getParameter("idTipo"));
        Integer idTipo = null;
        TipoProducto tipo = new TipoProducto();
        tipo.setId(idTipo);

        //OBTIENE LISTA DE TODOS LOS PRODUCTOS
        if (categoria == null || categoria == 0) {
            List<Producto> todosLosProductos = service.listar(tipo);
            req.setAttribute("todosLosProductos", todosLosProductos); //SE ENVIA A LA VISTA
        } else {

            //Integer productoTipo = 1; //AGREGAR UN LINK QUE MANDE ESTE ITEM
            List<Producto> todosLosProductos = service.listarByTipo(categoria, tipo);
            req.setAttribute("todosLosProductos", todosLosProductos); //SE ENVIA A LA VISTA

        }


        //USUARIO DISPONIBLE
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);
        req.setAttribute("username", usernameOptional);

        try {
            CarritoService carritoService = new CarritoServiceImpl();
            HttpSession session = req.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuario"); //SE RECUPERA EL USUARIO
            Integer userId = usuario.getIdUser(); //SE OBTIENE EL USER ID


            DecimalFormat formatea = new DecimalFormat("###,###,###");
            Carrito datos = carritoService.obtenerCarrito(userId);
            Integer nums = datos.getPrecioTotal();
            String precioTotal = formatea.format(nums);
            req.setAttribute("precioTotal", precioTotal);
            ////
        } catch (Exception e) {

        }
        if (usernameOptional.isPresent()) {
            getServletContext().getRequestDispatcher("/crud-eliminar.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/inicio");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        categoria = Integer.valueOf(req.getParameter("categoria"));

        resp.sendRedirect(req.getContextPath() + "/crud/eliminar");

    }
}
