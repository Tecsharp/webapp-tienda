package org.tecsharp.apiservlet.webapp.headers.repositories;

import org.tecsharp.apiservlet.webapp.headers.models.Carrito;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.models.TipoProducto;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {

    List<TipoProducto> listarTipoProducto() throws SQLException;
    List<Producto> listar() throws SQLException;
    List<Producto> listarByTipo(Integer productoTipo) throws SQLException;
    Producto obtenerProductoPorId(Integer productoTipo) throws SQLException;
    List<Producto> obtenerTodosLosProductos ();
    boolean agregarProductoAlCarrito(Integer productoID, Integer idUser);

    void actualizarCarritoPorProductoDuplicado(Integer productoID, Integer idUser, Integer numItems);

    public List<Producto> getCarrito(Integer userId); //CAMBIAR A getProductosCarrito

    Carrito obtenerCarrito (Integer idUser); //CAMBIAR A getCarrito

    List<Producto> listarCarrusel(Integer productoTipo) throws SQLException;
    T porID(Integer id) throws SQLException;
    void guardar (T t) throws SQLException;
    void eliminar (Integer id) throws SQLException;

}
