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
import org.tecsharp.apiservlet.webapp.headers.services.LoginService;
import org.tecsharp.apiservlet.webapp.headers.services.LoginServiceSessionImpl;
import org.tecsharp.apiservlet.webapp.headers.services.ProductoService;
import org.tecsharp.apiservlet.webapp.headers.services.ProductoServiceJdbcImpl;

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


        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService serviceTipoProducto = new ProductoServiceJdbcImpl(conn);

        /////
        try {
            HttpSession session = req.getSession();
            Usuario usuario = (Usuario)session.getAttribute("usuario"); //SE RECUPERA EL USUARIO
            Integer userId = usuario.getIdUser(); //SE OBTIENE EL USER ID

            //MANDA EL PRECIO TOTAL DEL CARRITO
            //Carrito carritoDatos = serviceTipoProducto.obtenerCarrito(userId);
            //req.setAttribute("carritoDatos", carritoDatos); //SE ENVIA A LA VISTA
            //RESPALDO INDEX.JSP
            //Carrito carritoDatos = (Carrito) request.getAttribute("carritoDatos");

            DecimalFormat formatea = new DecimalFormat("###,###,###");
            Carrito datos = serviceTipoProducto.obtenerCarrito(userId);
            Integer nums = datos.getPrecioTotal();
            String precioTotal = formatea.format(nums);

            req.setAttribute("precioTotal", precioTotal);


            ////
        } catch (Exception e){

        }



        //SERVICIO LISTA TIPO PRODUCTOS
        List<TipoProducto> categorias = serviceTipoProducto.listarTipoProducto();
        req.setAttribute("categorias", categorias); //SE ENVIA A LA VISTA

        //SERVICIO LISTA 4 PRODUCTO NUEVOS PRODUCTOS

        List<Producto> carruselUno = serviceTipoProducto.listarCarrusel(1);
        req.setAttribute("carruselUno", carruselUno); //SE ENVIA A LA VISTA

        //SERVICIO LISTA 4 PRODUCTO NUEVOS PRODUCTOS
        List<Producto> carruselDos = serviceTipoProducto.listarCarrusel(2);
        req.setAttribute("carruselDos", carruselDos); //SE ENVIA A LA VISTA

        req.setAttribute("username", usernameOptional);
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);

    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        LoginService auth = new LoginServiceSessionImpl();
//        Optional<String> usernameOptional = auth.getUsername(req);
//
//
//        Connection conn = (Connection) req.getAttribute("conn");
//        ProductoService serviceTipoProducto = new ProductoServiceJdbcImpl(conn);
//
//        //SERVICIO LISTA TIPO PRODUCTOS
//        List<TipoProducto> categorias = serviceTipoProducto.listarTipoProducto();
//        req.setAttribute("categorias", categorias); //SE ENVIA A LA VISTA
//
//        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
//
//    }

}
