package org.tecsharp.apiservlet.webapp.headers.services.producto.impl;

import org.tecsharp.apiservlet.webapp.headers.models.Carrito;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.models.TipoProducto;
import org.tecsharp.apiservlet.webapp.headers.services.producto.ProductoService;

import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoService {
    @Override
    public List<TipoProducto> listarTipoProducto() {
        return null;
    }

    @Override
    public List<Producto> listar() {
        return null;
    }

    @Override
    public List<Producto> listarByTipo(Integer productoTipo) {
        return null;
    }

    @Override
    public List<Producto> listarCarrusel(Integer productoTipo) {
        return null;
    }

    @Override
    public List<Producto> obtenerTodosLosProductos() {
        return null;
    }

    @Override
    public Producto obtenerProductoPorId(Integer id) {
        return null;
    }

    @Override
    public Optional<Producto> porId(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean agregarProductoAlCarrito(Integer productoID, Integer idUser) {
        return false;
    }

    @Override
    public List<Producto> getCarrito(Integer userId) {
        return null;
    }

    @Override
    public void actualizarCarritoPorProductoDuplicado(Integer productoID, Integer idUser, Integer numItems) {

    }

    @Override
    public boolean validaProductoEnCarrito(boolean enCarrito) {
        if(enCarrito){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Carrito obtenerCarrito(Integer idUser) {
        return null;
    }
}
