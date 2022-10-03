package org.tecsharp.apiservlet.webapp.headers.controllers.carrito;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.models.Usuario;
import org.tecsharp.apiservlet.webapp.headers.services.carrito.CarritoService;
import org.tecsharp.apiservlet.webapp.headers.services.carrito.impl.CarritoServiceImpl;
import org.tecsharp.apiservlet.webapp.headers.services.login.LoginService;
import org.tecsharp.apiservlet.webapp.headers.services.login.impl.LoginServiceSessionImpl;

import java.io.IOException;
import java.sql.Connection;

import java.util.List;
import java.util.Optional;

@WebServlet("/ver/carrito")
public class VerCarritoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Se establece conexion con bd
        Connection conn = (Connection) req.getAttribute("conn");
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
            List<Producto> proca = carritoService.getCarrito(userId);
            req.setAttribute("proca", proca);

            //SE ENVIA EL PRECIO TOTAL A PAGAR CON FORMATO
            String precioTotal = carritoService.obtenerPrecioTotalFormateado(userId);
            req.setAttribute("precioTotal", precioTotal);

        } catch (Exception e){

        }

        //SE OBTIENE EL USUARIO DE LA SESION
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);
        req.setAttribute("username", usernameOptional);

        getServletContext().getRequestDispatcher("/cart.jsp").forward(req, resp);
    }

}
