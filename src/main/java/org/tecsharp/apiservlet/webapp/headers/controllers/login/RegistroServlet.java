package org.tecsharp.apiservlet.webapp.headers.controllers.login;

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
import org.tecsharp.apiservlet.webapp.headers.services.login.impl.LoginServiceImpl;
import org.tecsharp.apiservlet.webapp.headers.services.login.impl.LoginServiceSessionImpl;
import org.tecsharp.apiservlet.webapp.headers.services.producto.ProductoService;
import org.tecsharp.apiservlet.webapp.headers.services.producto.impl.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //SE OBTIENE EL USUARIO
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        //CONEXION BDD
        Connection conn = (Connection) req.getAttribute("conn");
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


        if (!usernameOptional.isPresent()) {
            getServletContext().getRequestDispatcher("/registro.jsp").forward(req, resp);
        } else if (usernameOptional.isPresent()) {
            resp.sendRedirect(req.getContextPath() + "/index.html");
        } else {
            getServletContext().getRequestDispatcher("/registro").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //CONEXION BDD
        Connection conn = (Connection) req.getAttribute("conn");

        //IMPLEMENT SERVICE
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        List<TipoProducto> categorias = service.listarTipoProducto();
        req.setAttribute("categorias", categorias); //SE ENVIA A LA VISTA

        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        LoginService loginService = new LoginServiceImpl();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        if (!usernameOptional.isPresent() && loginService.registrarUsuario(nombre, apellido, email, username, password)) {
            getServletContext().getRequestDispatcher("/registro-exitoso.jsp").forward(req, resp);
        } else if(usernameOptional.isPresent()) {
            resp.sendRedirect(req.getContextPath() + "/index.html");
        } else {
            getServletContext().getRequestDispatcher("/registro.jsp").forward(req, resp);
        }

    }
}
