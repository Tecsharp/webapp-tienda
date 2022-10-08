package org.tecsharp.apiservlet.webapp.headers.repositories.producto;

import org.tecsharp.apiservlet.webapp.headers.models.Carrito;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.models.TipoProducto;

import java.sql.SQLException;
import java.util.List;

public interface ProductoRepository<T> {

    List<TipoProducto> listarTipoProducto() throws SQLException;
    List<Producto> listar(TipoProducto tipo) throws SQLException;
    List<Producto> listarByTipo(Integer productoTipo, TipoProducto tipo) throws SQLException;
    Producto obtenerProductoPorId(Integer id, TipoProducto tipo) throws SQLException;
    List<Producto> obtenerTodosLosProductos ();
    List<Producto> listarCarrusel(Integer productoTipo, TipoProducto tipo) throws SQLException;
    List<Producto> listarProductoRandom(Integer limite) throws SQLException;
    T porID(Integer id, TipoProducto tipo) throws SQLException;
    void guardar (T t) throws SQLException;
    void eliminar (Integer id) throws SQLException;
    List<Producto> obtenerTodosLosProductosPorCategoria(Integer categoria);
    boolean eliminarProductoPorId(Integer idProducto);
    Integer obtenerNumeroDeProductos();
    List<Producto> listarProductosPopulares(Integer limite) throws SQLException;
    List<Producto> buscarProductos(String busqueda);


}
