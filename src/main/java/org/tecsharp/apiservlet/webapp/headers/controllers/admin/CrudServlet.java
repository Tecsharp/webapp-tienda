package org.tecsharp.apiservlet.webapp.headers.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.tecsharp.apiservlet.webapp.headers.models.Usuario;
import org.tecsharp.apiservlet.webapp.headers.services.crud.CrudService;
import org.tecsharp.apiservlet.webapp.headers.services.crud.impl.CrudServiceImpl;
import org.tecsharp.apiservlet.webapp.headers.services.login.LoginService;
import org.tecsharp.apiservlet.webapp.headers.services.login.impl.LoginServiceSessionImpl;
import org.tecsharp.apiservlet.webapp.headers.services.producto.ProductoService;
import org.tecsharp.apiservlet.webapp.headers.services.producto.impl.ProductoServiceJdbcImpl;
import org.tecsharp.apiservlet.webapp.headers.services.usuario.UsuarioService;
import org.tecsharp.apiservlet.webapp.headers.services.usuario.impl.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;


@WebServlet("/crud")
public class CrudServlet extends HttpServlet {

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


        //SE ENVIAN LOS DATOS DE ESTADISTICA


        //OBTENER USERTYPE (ADMIN)
        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario"); //SE RECUPERA EL USUARIO
        //Integer userId = usuario.getIdUser(); //SE OBTIENE EL USER ID


        if (usernameOptional.isPresent() && usuario.getUserType() == 2) {
            getServletContext().getRequestDispatcher("/crud.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/inicio");
        }

    }

}
