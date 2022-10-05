package org.tecsharp.apiservlet.webapp.headers.controllers.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.tecsharp.apiservlet.webapp.headers.models.Carrito;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.models.TipoProducto;
import org.tecsharp.apiservlet.webapp.headers.models.Usuario;
import org.tecsharp.apiservlet.webapp.headers.repositories.carrito.CarritoRepository;
import org.tecsharp.apiservlet.webapp.headers.repositories.carrito.impl.CarritoRepositoryImpl;
import org.tecsharp.apiservlet.webapp.headers.services.carrito.CarritoService;
import org.tecsharp.apiservlet.webapp.headers.services.carrito.impl.CarritoServiceImpl;
import org.tecsharp.apiservlet.webapp.headers.services.login.LoginService;
import org.tecsharp.apiservlet.webapp.headers.services.login.impl.LoginServiceSessionImpl;
import org.tecsharp.apiservlet.webapp.headers.services.producto.ProductoService;
import org.tecsharp.apiservlet.webapp.headers.services.producto.impl.ProductoServiceJdbcImpl;
import org.tecsharp.apiservlet.webapp.headers.services.usuario.UsuarioService;
import org.tecsharp.apiservlet.webapp.headers.services.usuario.impl.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@WebServlet({"/inicio"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //SE OBTIENE EL USUARIO
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        //CONEXION BDD
        Connection conn = (Connection) req.getAttribute("conn" );
        ProductoService serviceTipoProducto = new ProductoServiceJdbcImpl(conn);

        //SERVICIO LISTA TIPO PRODUCTOS
        List<TipoProducto> categorias = serviceTipoProducto.listarTipoProducto();
        req.setAttribute("categorias", categorias); //SE ENVIA A LA VISTA

        TipoProducto tipo1 = new TipoProducto();
        tipo1.setId(1);

        TipoProducto tipo2 = new TipoProducto();
        tipo1.setId(2);

        //SERVICIO LISTA 4 PRODUCTO NUEVOS PRODUCTOS
        List<Producto> carruselUno = serviceTipoProducto.listarCarrusel(1, tipo1);
        req.setAttribute("carruselUno", carruselUno); //SE ENVIA A LA VISTA

        //SERVICIO LISTA 4 PRODUCTO NUEVOS PRODUCTOS
        List<Producto> carruselDos = serviceTipoProducto.listarCarrusel(2, tipo2);
        req.setAttribute("carruselDos", carruselDos); //SE ENVIA A LA VISTA
        req.setAttribute("username", usernameOptional);

        try {
            HttpSession session = req.getSession();
            Usuario usuario = (Usuario)session.getAttribute("usuario"); //SE RECUPERA EL USUARIO
            Integer userId = usuario.getIdUser(); //SE OBTIENE EL USER ID

            CarritoService carritoService = new CarritoServiceImpl();
            DecimalFormat formatea = new DecimalFormat("###,###,###");
            Carrito datos = carritoService.obtenerCarrito(userId);
            Integer nums = datos.getPrecioTotal();
            String precioTotal = formatea.format(nums);
            req.setAttribute("precioTotal", precioTotal);

            //SE ENVIA CANTIDAD DE ITEMS EN CARRITO
            CarritoRepository serviceCarrito = new CarritoRepositoryImpl();
            Integer productosEnCarrito = serviceCarrito.obtenerCantidadItemsCarrito(usuario.getIdUser());
            req.setAttribute("productosEnCarrito", productosEnCarrito);

            ////
        } catch (Exception e){

        }

        if (usernameOptional.isPresent()) {
            getServletContext().getRequestDispatcher("/index.html").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/inicio.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //SERVICIO USUARIO
        UsuarioService usuarioService = new UsuarioServiceImpl();
        Usuario usuario = usuarioService.login(username, password);

        if (usuario != null) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            req.setAttribute("usuario", usuario); // add to request
            req.getSession().setAttribute("usuario", usuario); // add to session
            resp.sendRedirect(req.getContextPath() + "/inicio");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado para ingresar a esta página!");
        }
    }
}
