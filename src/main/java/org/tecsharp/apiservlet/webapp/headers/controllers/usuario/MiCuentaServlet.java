package org.tecsharp.apiservlet.webapp.headers.controllers.usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.tecsharp.apiservlet.webapp.headers.models.Carrito;
import org.tecsharp.apiservlet.webapp.headers.models.Usuario;
import org.tecsharp.apiservlet.webapp.headers.repositories.carrito.CarritoRepository;
import org.tecsharp.apiservlet.webapp.headers.repositories.carrito.impl.CarritoRepositoryImpl;
import org.tecsharp.apiservlet.webapp.headers.services.crud.CrudService;
import org.tecsharp.apiservlet.webapp.headers.services.crud.impl.CrudServiceImpl;
import org.tecsharp.apiservlet.webapp.headers.services.login.LoginService;
import org.tecsharp.apiservlet.webapp.headers.services.login.impl.LoginServiceSessionImpl;
import org.tecsharp.apiservlet.webapp.headers.services.producto.ProductoService;
import org.tecsharp.apiservlet.webapp.headers.services.producto.impl.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;


@WebServlet("/mi-cuenta")
public class MiCuentaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //SE OBTIENE EL USUARIO
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);
        //SE ENVIA EL USUARIO
        req.setAttribute("username", usernameOptional);

        try {
            HttpSession session = req.getSession();
            Usuario usuario = (Usuario)session.getAttribute("usuario"); //SE RECUPERA EL USUARIO
            Integer userId = usuario.getIdUser(); //SE OBTIENE EL USER ID
            req.setAttribute("usuario", usuario); //SE ENVIA AL REQUEST

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
            getServletContext().getRequestDispatcher("/mi-cuenta.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/inicio");
        }
    }
}
