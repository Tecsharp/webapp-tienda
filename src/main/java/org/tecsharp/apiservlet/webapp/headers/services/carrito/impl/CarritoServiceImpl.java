package org.tecsharp.apiservlet.webapp.headers.services.carrito.impl;

import org.tecsharp.apiservlet.webapp.headers.models.Carrito;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.repositories.carrito.CarritoRepository;
import org.tecsharp.apiservlet.webapp.headers.repositories.carrito.impl.CarritoRepositoryImpl;
import org.tecsharp.apiservlet.webapp.headers.services.carrito.CarritoService;
import org.tecsharp.apiservlet.webapp.headers.services.login.LoginService;
import org.tecsharp.apiservlet.webapp.headers.services.login.impl.LoginServiceSessionImpl;

import java.text.DecimalFormat;
import java.util.List;

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
}
