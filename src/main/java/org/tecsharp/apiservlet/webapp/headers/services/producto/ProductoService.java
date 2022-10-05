package org.tecsharp.apiservlet.webapp.headers.services.producto;

import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.models.TipoProducto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<TipoProducto> listarTipoProducto();
    List<Producto> listar(TipoProducto tipo);
    List<Producto> listarByTipo(Integer productoTipo, TipoProducto tipo);
    List<Producto> listarCarrusel(Integer productoTipo,TipoProducto tipo);
    List<Producto> obtenerTodosLosProductos ();
    Producto obtenerProductoPorId (Integer id, TipoProducto tipo);
    Optional<Producto> porId(Integer id, TipoProducto tipo);
    boolean eliminarProductoPorId( Integer idProducto);

    boolean validaProductoEnCarrito (boolean enCarrito);


}
