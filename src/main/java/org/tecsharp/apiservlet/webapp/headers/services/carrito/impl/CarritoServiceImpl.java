package org.tecsharp.apiservlet.webapp.headers.services.carrito.impl;

import org.tecsharp.apiservlet.webapp.headers.models.Carrito;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.models.Usuario;
import org.tecsharp.apiservlet.webapp.headers.models.Ventas;
import org.tecsharp.apiservlet.webapp.headers.repositories.carrito.CarritoRepository;
import org.tecsharp.apiservlet.webapp.headers.repositories.carrito.impl.CarritoRepositoryImpl;
import org.tecsharp.apiservlet.webapp.headers.repositories.producto.ProductoRepository;
import org.tecsharp.apiservlet.webapp.headers.repositories.producto.impl.ProductoRepositoryJdbcImpl;
import org.tecsharp.apiservlet.webapp.headers.services.carrito.CarritoService;
import org.tecsharp.apiservlet.webapp.headers.services.login.LoginService;
import org.tecsharp.apiservlet.webapp.headers.services.login.impl.LoginServiceSessionImpl;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

public class CarritoServiceImpl implements CarritoService {
    //IMPLEMENTS
    CarritoRepository service = new CarritoRepositoryImpl();


    @Override
    public Integer obtenerCantidadItemsCarrito(Integer idUser) {
        return service.obtenerCantidadItemsCarrito(idUser);
    }

    @Override
    public Carrito obtenerCarrito(Integer idUser) {
        return service.obtenerCarrito(idUser);
    }

    @Override
    public List<Producto> getCarrito(Integer idUser) {
        return service.getCarrito(idUser);
    }

    @Override
    public String obtenerPrecioTotalFormateado(Integer idUser) {
        DecimalFormat formatea = new DecimalFormat("###,###,###");
        Carrito datos = service.obtenerCarrito(idUser);
        Integer nums = datos.getPrecioTotal();

        return formatea.format(nums);
    }

    @Override
    public boolean agregarProductoAlCarrito(Integer productoID, Integer idUser, Integer numItems) {

        boolean productoDuplicado = esProductoCarritoDuplicado(productoID, idUser);

        return service.agregarProductoAlCarrito(productoID, idUser, numItems, productoDuplicado);
    }


    @Override
    public void actualizarCarritoPorProductoDuplicado(Integer productoID, Integer idUser, Integer numItems) {

    }



    @Override
    public boolean esProductoCarritoDuplicado(Integer productoID, Integer idUser) {

        CarritoRepository productoCarrito = new CarritoRepositoryImpl();

        List<Producto> listCarrito = productoCarrito.getCarrito(idUser);

        for (Producto producto : listCarrito) {

            if (Objects.equals(producto.getId(), productoID)) {
                //System.out.println("Producto duplicado");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminarPorductoDeCarrito(Integer idProducto, Integer idUser) {
        return service.eliminarPorductoDeCarrito(idProducto, idUser);
    }

    @Override
    public boolean comprarCarrito(Integer idUser) {


        CarritoRepository carritoRepository = new CarritoRepositoryImpl();
        List<Producto> carrito = carritoRepository.getCarrito(idUser); //OBTENGO EL CARRITO DEL USUARIO

        return carritoRepository.comprarCarrito(carrito, idUser);
    }

    @Override
    public List<Ventas> getVentas(Integer userId) {

        return service.getVentas(userId);
    }


}
