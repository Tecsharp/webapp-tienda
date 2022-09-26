package org.tecsharp.apiservlet.webapp.headers.services.carrito.impl;

import org.tecsharp.apiservlet.webapp.headers.repositories.carrito.CarritoRepository;
import org.tecsharp.apiservlet.webapp.headers.repositories.carrito.impl.CarritoRepositoryImpl;
import org.tecsharp.apiservlet.webapp.headers.services.carrito.CarritoService;

public class CarritoServiceImpl implements CarritoService {
    @Override
    public Integer obtenerCantidadItemsCarrito(Integer idUser) {
        CarritoRepository service = new CarritoRepositoryImpl();
        return service.obtenerCantidadItemsCarrito(idUser);
    }
}
