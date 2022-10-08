package org.tecsharp.apiservlet.webapp.headers.controllers.admin.agregar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.models.Usuario;
import org.tecsharp.apiservlet.webapp.headers.services.crud.CrudService;
import org.tecsharp.apiservlet.webapp.headers.services.crud.impl.CrudServiceImpl;
import org.tecsharp.apiservlet.webapp.headers.services.login.LoginService;
import org.tecsharp.apiservlet.webapp.headers.services.login.impl.LoginServiceSessionImpl;
import org.tecsharp.apiservlet.webapp.headers.services.usuario.UsuarioService;
import org.tecsharp.apiservlet.webapp.headers.services.usuario.impl.UsuarioServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@WebServlet("/crud/agregar")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class CrudAgregarServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //SE OBTIENE EL USUARIO
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);
        req.setAttribute("username", usernameOptional);

        //SE OBTIENE EL OBJETO USUARIO
        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario"); //SE RECUPERA EL USUARIO

        if (usernameOptional.isPresent() && usuario.getUserType() == 2) {
            getServletContext().getRequestDispatcher("/crud-agregar.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/inicio");
        }


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //SE OBTIENEN LOS DATOS
        Integer categoria = Integer.valueOf(req.getParameter("categoria"));
        String nombre = req.getParameter("nombre");
        Integer precio = Integer.valueOf(req.getParameter("precio"));
        Integer stock = Integer.valueOf(req.getParameter("stock"));
        String shortDescription = req.getParameter("shortdescription");
        String largeDescription = req.getParameter("largedescription");
        Integer status = Integer.valueOf(req.getParameter("status"));

        //SE OBTIENE EL USUARIO, SU ID Y SU TIPO USUARIO
        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario"); //SE RECUPERA EL USUARIO
        Integer userId = usuario.getIdUser(); //SE OBTIENE EL USER ID
        Integer userType = usuario.getUserType();
        CrudService crudService = new CrudServiceImpl();

        //RECIBE LA IMAGEN

        String contextPath = "/Users/tecsharp/tomcat/webapps";
        String serverPath = "/webapp-tienda/assets/img/products/nuevos/";
        final String FILE_DIRECTORY = contextPath + serverPath;

        Part filePart = req.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        for (Part part : req.getParts()) {
            part.write( FILE_DIRECTORY + fileName);
        }

        //ENVIA EL PATH DE LA IMAGEN A BASE DE DATOS SEGUN EL PRODUCTO
        String ubicacionImg = serverPath + fileName;

        /////

        if (usuario.getUserType() == 2 && crudService.registrarNuevoProducto(userId, userType, categoria, nombre, precio, stock, shortDescription, largeDescription, status, ubicacionImg)) {
            getServletContext().getRequestDispatcher("/crud/agregar/valid.html").forward(req, resp);

        } else {
            resp.sendRedirect(req.getContextPath() + "/crud/agregar");
        }
    }
}
