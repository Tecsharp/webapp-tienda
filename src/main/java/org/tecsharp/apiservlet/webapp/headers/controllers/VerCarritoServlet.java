package org.tecsharp.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.tecsharp.apiservlet.webapp.headers.models.Carrito;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.models.Usuario;
import org.tecsharp.apiservlet.webapp.headers.repositories.carrito.CarritoRepository;
import org.tecsharp.apiservlet.webapp.headers.repositories.carrito.impl.CarritoRepositoryImpl;
import org.tecsharp.apiservlet.webapp.headers.services.*;

import java.io.IOException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@WebServlet("/ver/carrito")
public class VerCarritoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ///
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        //Integer idUser = Integer.valueOf(req.getParameter("idUser"));
        try {
            HttpSession session = req.getSession();
            Usuario usuario = (Usuario)session.getAttribute("usuario"); //SE RECUPERA EL USUARIO
            Integer userId = usuario.getIdUser(); //SE OBTIENE EL USER ID

            //SE ENVIA CANTIDAD DE ITEMS EN CARRITO
            CarritoRepository serviceCarrito = new CarritoRepositoryImpl();
            Integer productosEnCarrito = serviceCarrito.obtenerCantidadItemsCarrito(usuario.getIdUser());
            req.setAttribute("productosEnCarrito", productosEnCarrito);



            DecimalFormat formatea = new DecimalFormat("###,###,###");
            Carrito datos = service.obtenerCarrito(userId);
            Integer nums = datos.getPrecioTotal();
            String precioTotal = formatea.format(nums);

            req.setAttribute("precioTotal", precioTotal);

            ////
        } catch (Exception e){

        }




        //SE RECUPERA LA SESION
        HttpSession session = req.getSession();
        Usuario usuario = (Usuario)session.getAttribute("usuario"); //SE RECUPERA EL USUARIO
        Integer userId = usuario.getIdUser(); //SE OBTIENE EL USER ID


        List<Producto> proca = service.getCarrito(userId);
        req.setAttribute("proca", proca);

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);


        req.setAttribute("username", usernameOptional);

        getServletContext().getRequestDispatcher("/cart.jsp").forward(req, resp);
    }

}
