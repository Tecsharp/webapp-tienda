package org.tecsharp.apiservlet.webapp.headers.controllers.admin.eliminar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.models.TipoProducto;
import org.tecsharp.apiservlet.webapp.headers.models.Usuario;
import org.tecsharp.apiservlet.webapp.headers.services.login.LoginService;
import org.tecsharp.apiservlet.webapp.headers.services.login.impl.LoginServiceSessionImpl;
import org.tecsharp.apiservlet.webapp.headers.services.producto.ProductoService;
import org.tecsharp.apiservlet.webapp.headers.services.producto.impl.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/crud/eliminar/producto")
public class CrudEliminarProducto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        Integer idProducto = Integer.valueOf(req.getParameter("id"));
        ProductoService service = new ProductoServiceJdbcImpl(conn);

        //SE RECUPERA LA ID DE LA CATEGORIA
        Integer idTipo = null;
        TipoProducto tipoProducto = new TipoProducto();
        tipoProducto.setId(idTipo);

        //SE ENVIA PRODUCTO
        Producto producto = service.obtenerProductoPorId(idProducto, tipoProducto);
        req.setAttribute("producto", producto);

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario"); //SE RECUPERA EL USUARIO
        Integer userId = usuario.getIdUser(); //SE OBTIENE EL USER ID

        if (usernameOptional.isPresent() && usuario.getUserType() == 2) {
            if (service.eliminarProductoPorId(idProducto)) {

                //ENVIA PRODUCTO ELIMINADO
                getServletContext().getRequestDispatcher("/crud/eliminar/valid.html").forward(req, resp);
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/inicio");
        }


    }
}
