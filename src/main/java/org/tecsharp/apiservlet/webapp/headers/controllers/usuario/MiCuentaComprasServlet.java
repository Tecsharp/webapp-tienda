package org.tecsharp.apiservlet.webapp.headers.controllers.usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.models.Usuario;
import org.tecsharp.apiservlet.webapp.headers.models.Ventas;
import org.tecsharp.apiservlet.webapp.headers.services.carrito.CarritoService;
import org.tecsharp.apiservlet.webapp.headers.services.carrito.impl.CarritoServiceImpl;
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


@WebServlet("/ver/compras")
public class MiCuentaComprasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //SE OBTIENE EL USUARIO
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        //SE OBTIENE EL ADMIN
        Optional<Integer> userAdminOptional = auth.getUserType(req);
        req.setAttribute("userAdminOptional", userAdminOptional);

        //SE ENVIA EL USUARIO
        req.setAttribute("username", usernameOptional);


        //IMPLEMENTS
        CarritoService carritoService = new CarritoServiceImpl();

        try {
            HttpSession session = req.getSession();
            Usuario usuario = (Usuario)session.getAttribute("usuario"); //SE RECUPERA EL USUARIO
            Integer userId = usuario.getIdUser(); //SE OBTIENE EL USER ID

            //SE ENVIA CANTIDAD DE ITEMS EN CARRITO
            Integer numeroProductosEnCarrito = carritoService.obtenerCantidadItemsCarrito(usuario.getIdUser());
            req.setAttribute("numeroProductosEnCarrito", numeroProductosEnCarrito);

            //SE ENVIA LA LISTA DE PRODUCTOS EN CARRITO
            List<Ventas> proca = carritoService.getVentas(userId);
            req.setAttribute("proca", proca);
            proca.get(2).getProductType().getId();


            //SE ENVIA EL PRECIO TOTAL A PAGAR CON FORMATO
            String precioTotal = carritoService.obtenerPrecioTotalFormateado(userId);
            req.setAttribute("precioTotal", precioTotal);

        } catch (Exception e){

        }


        //SERVICES
        Connection conn = (Connection) req.getAttribute("conn"); //SE OBTIENE CONEXION DE SESION
        ProductoService productoService = new ProductoServiceJdbcImpl(conn);
        Integer numProductos = productoService.obtenerNumeroDeProductos();
        req.setAttribute("numProductos", numProductos);

        CrudService crudService = new CrudServiceImpl();
        Integer numCategorias = crudService.obtenerNumeroCategorias();
        Integer numUsuariosRegistrados = crudService.obtenerNumeroDeUsuariosRegistrados();

        req.setAttribute("numCategorias", numCategorias);
        req.setAttribute("numUsuariosRegistrados", numUsuariosRegistrados);


        //OBTENER USERTYPE (ADMIN)
        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario"); //SE RECUPERA EL USUARIO
        //Integer userId = usuario.getIdUser(); //SE OBTIENE EL USER ID


        if (usernameOptional.isPresent()) {
            getServletContext().getRequestDispatcher("/mi-cuenta-compras.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/inicio");
        }
    }
}
