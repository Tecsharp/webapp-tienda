package org.tecsharp.apiservlet.webapp.headers.services.producto;

import org.tecsharp.apiservlet.webapp.headers.models.Carrito;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.models.TipoProducto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<TipoProducto> listarTipoProducto();
    List<Producto> listar();
    List<Producto> listarByTipo(Integer productoTipo);
    List<Producto> listarCarrusel(Integer productoTipo);
    List<Producto> obtenerTodosLosProductos ();
    Producto obtenerProductoPorId (Integer id);
    Optional<Producto> porId(Integer id);

    boolean validaProductoEnCarrito (boolean enCarrito);


}
