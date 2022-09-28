package org.tecsharp.apiservlet.webapp.headers.repositories.carrito;

import org.tecsharp.apiservlet.webapp.headers.models.Carrito;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.models.Usuario;

import java.util.List;

public interface CarritoRepository {

    Integer obtenerCantidadItemsCarrito(Integer idUser);
    Carrito obtenerCarrito(Integer idUser);
    List<Producto> getCarrito(Integer userId); //CAMBIAR A getProductosCarrito
    boolean agregarProductoAlCarrito(Integer productoID, Integer idUser, Integer numItems, boolean productoDuplicado);
    void actualizarCarritoPorProductoDuplicado(Integer productoID, Integer idUser, Integer numItems);
    boolean eliminarPorductoDeCarrito (Integer idProducto, Integer idCarrito);
}
