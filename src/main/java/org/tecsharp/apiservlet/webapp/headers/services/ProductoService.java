package org.tecsharp.apiservlet.webapp.headers.services;

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
    boolean agregarProductoAlCarrito(Integer productoID, Integer idUser);
    List<Producto> getCarrito(Integer userId);
    void actualizarCarritoPorProductoDuplicado(Integer productoID, Integer idUser, Integer numItems);
    boolean validaProductoEnCarrito (boolean enCarrito);

    Carrito obtenerCarrito(Integer idUser); //AGREGAR EN CARRITOSERVICE

}
