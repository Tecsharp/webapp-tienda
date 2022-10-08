package org.tecsharp.apiservlet.webapp.headers.controllers;

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
import org.tecsharp.apiservlet.webapp.headers.repositories.carrito.CarritoRepository;
import org.tecsharp.apiservlet.webapp.headers.repositories.carrito.impl.CarritoRepositoryImpl;
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


@WebServlet({"/index.html"})
public class IndexServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);
        Optional<Integer> userAdminOptional = auth.getUserType(req);
        req.setAttribute("username", usernameOptional);
        req.setAttribute("userAdminOptional", userAdminOptional);

        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService serviceProducto = new ProductoServiceJdbcImpl(conn);
        CarritoService carritoService = new CarritoServiceImpl();



        /////
        try {
            HttpSession session = req.getSession();
            Usuario usuario = (Usuario)session.getAttribute("usuario"); //SE RECUPERA EL USUARIO
            Integer userId = usuario.getIdUser(); //SE OBTIENE EL USER ID
            req.setAttribute("usuario", usuario); //SE ENVIA AL REQUEST

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


        //SERVICIO LISTA TIPO PRODUCTOS
        List<TipoProducto> categorias = serviceProducto.listarTipoProducto();
        req.setAttribute("categorias", categorias); //SE ENVIA A LA VISTA

        List<Producto> productosRandom = serviceProducto.listarProductoRandom(3);
        req.setAttribute("productosRandom", productosRandom);

        List<Producto> productosPopulares = serviceProducto.listarProductosPopulares(3);
        req.setAttribute("productosPopulares", productosPopulares);

        //SERVICIO LISTA 4 PRODUCTO NUEVOS PRODUCTOS
        //SE RECUPERA LA ID DE LA CATEGORIA

        TipoProducto tipo1 = new TipoProducto();
        tipo1.setId(1);

        TipoProducto tipo2 = new TipoProducto();
        tipo2.setId(2);

        List<Producto> carruselUno = serviceProducto.listarCarrusel(1, tipo1);
        req.setAttribute("carruselUno", carruselUno); //SE ENVIA A LA VISTA

        //SERVICIO LISTA 4 PRODUCTO NUEVOS PRODUCTOS
        List<Producto> carruselDos = serviceProducto.listarCarrusel(2, tipo2);
        req.setAttribute("carruselDos", carruselDos); //SE ENVIA A LA VISTA
       //carruselDos.get(2).getTipo().getId();


        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);

    }

}
