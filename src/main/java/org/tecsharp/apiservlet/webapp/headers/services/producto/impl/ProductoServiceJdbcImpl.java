package org.tecsharp.apiservlet.webapp.headers.services.producto.impl;

import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.models.TipoProducto;
import org.tecsharp.apiservlet.webapp.headers.repositories.producto.impl.ProductoRepositoryJdbcImpl;
import org.tecsharp.apiservlet.webapp.headers.services.ServiceJdbcException;
import org.tecsharp.apiservlet.webapp.headers.services.producto.ProductoService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImpl implements ProductoService {
    private ProductoRepositoryJdbcImpl repositoryJdbc;

    public ProductoServiceJdbcImpl(Connection connection) {
        this.repositoryJdbc = new ProductoRepositoryJdbcImpl(connection);
    }


    @Override
    public List<TipoProducto> listarTipoProducto() {
        try {
            return repositoryJdbc.listarTipoProducto();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }



    @Override
    public List<Producto> listar(TipoProducto tipo) {
        try {
            return repositoryJdbc.listar(tipo);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<Producto> listarByTipo(Integer productoTipo, TipoProducto tipo) {
        try {
            return repositoryJdbc.listarByTipo(productoTipo, tipo);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<Producto> listarCarrusel(Integer productoTipo, TipoProducto tipo) {
        try {
            return repositoryJdbc.listarCarrusel(productoTipo, tipo);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<Producto> obtenerTodosLosProductos() {
        return repositoryJdbc.obtenerTodosLosProductos();
    }

    @Override
    public Producto obtenerProductoPorId(Integer id, TipoProducto tipo) {
        try {
            return repositoryJdbc.obtenerProductoPorId(id, tipo);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Producto> porId(Integer id, TipoProducto tipo) {
        try {
            return Optional.ofNullable(repositoryJdbc.porID(id, tipo));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public boolean eliminarProductoPorId(Integer idProducto) {
        return repositoryJdbc.eliminarProductoPorId(idProducto);
    }


    @Override
    public boolean validaProductoEnCarrito(boolean enCarrito) {
        if(!enCarrito){
            return false;
        } else {
            return true;
        }
    }


}
