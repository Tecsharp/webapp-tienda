package org.tecsharp.apiservlet.webapp.headers.repositories.carrito;

import org.tecsharp.apiservlet.webapp.headers.models.Carrito;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.models.Usuario;
import org.tecsharp.apiservlet.webapp.headers.models.Ventas;

import java.util.List;

public interface CarritoRepository {

    Integer obtenerCantidadItemsCarrito(Integer idUser);
    Carrito obtenerCarrito(Integer idUser);
    List<Producto> getCarrito(Integer userId); //CAMBIAR A getProductosCarrito
    boolean agregarProductoAlCarrito(Integer productoID, Integer idUser, Integer numItems, boolean productoDuplicado);
    void actualizarCarritoPorProductoDuplicado(Integer productoID, Integer idUser, Integer numItems);
    boolean eliminarPorductoDeCarrito (Integer idProducto, Integer idCarrito);
    boolean comprarCarrito(List<Producto> productos ,Integer idUser);
    boolean limpiarCarrito(Integer idUser);
    boolean reducirStockPorCompra(List<Producto> productos);


    List<Ventas> getVentas(Integer userId);

    boolean agregarVentasAProductos(Integer productoID, Integer idUser, Integer numItems);
}
