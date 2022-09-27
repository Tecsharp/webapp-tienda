package org.tecsharp.apiservlet.webapp.headers.repositories.carrito;

import org.tecsharp.apiservlet.webapp.headers.models.Carrito;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;

import java.util.List;

public interface CarritoRepository {

    Integer obtenerCantidadItemsCarrito(Integer idUser);
    Carrito obtenerCarrito(Integer idUser);
    List<Producto> getCarrito(Integer userId); //CAMBIAR A getProductosCarrito

}
