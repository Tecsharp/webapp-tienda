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

@WebServlet({"/productos"})
public class CategoriaProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn" );

        //SE OBTIENE EL ID DE LA CATEGORIA
        Integer idCat = Integer.valueOf(req.getParameter("idCat"));

        //PRODUCTOS DE MOTOCICLETA
        ProductoService serviceProducto = new ProductoServiceJdbcImpl(conn);
        //Integer productoTipo = 1; //AGREGAR UN LINK QUE MANDE ESTE ITEM
        List<Producto> tipoProductos = serviceProducto.listarByTipo(idCat);
        req.setAttribute("productos", tipoProductos); //SE ENVIA A LA VISTA

        //LISTA DE CATEGORIAS
        List<TipoProducto> categorias = serviceProducto.listarTipoProducto();
        req.setAttribute("categorias", categorias); //SE ENVIA A LA VISTA

        //SE OBTIENE EL USUARIO
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req); //SE PREPARA
        req.setAttribute("username", usernameOptional); //SE ENVIA A LA VISTA

        try {
            CarritoService carritoService = new CarritoServiceImpl();
            HttpSession session = req.getSession();
            Usuario usuario = (Usuario)session.getAttribute("usuario"); //SE RECUPERA EL USUARIO
            Integer userId = usuario.getIdUser(); //SE OBTIENE EL USER ID


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

        getServletContext().getRequestDispatcher("/productos_categoria.jsp").forward(req, resp);
    }
}
