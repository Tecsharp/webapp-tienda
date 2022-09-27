package org.tecsharp.apiservlet.webapp.headers.services.carrito;

import org.tecsharp.apiservlet.webapp.headers.models.Carrito;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;

import java.util.List;

public interface CarritoService {

    Integer obtenerCantidadItemsCarrito(Integer idUser);

    Carrito obtenerCarrito(Integer idUser); //AGREGAR EN CARRITOSERVICE

    List<Producto> getCarrito(Integer idUser);

    String obtenerPrecioTotalFormateado(Integer idUser);

}
