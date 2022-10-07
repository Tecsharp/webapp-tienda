package org.tecsharp.apiservlet.webapp.headers.services.crud.impl;

import org.tecsharp.apiservlet.webapp.headers.repositories.crud.CrudRepository;
import org.tecsharp.apiservlet.webapp.headers.repositories.crud.impl.CrudRepositoryImpl;
import org.tecsharp.apiservlet.webapp.headers.repositories.tipoproducto.TipoProductoRepository;
import org.tecsharp.apiservlet.webapp.headers.repositories.tipoproducto.impl.TipoProductoRepositoryImpl;
import org.tecsharp.apiservlet.webapp.headers.repositories.usuario.UsuarioRepository;
import org.tecsharp.apiservlet.webapp.headers.repositories.usuario.UsuarioRepositoryImpl;
import org.tecsharp.apiservlet.webapp.headers.services.crud.CrudService;

public class CrudServiceImpl implements CrudService {
    @Override
    public boolean registrarNuevoProducto(Integer idUser, Integer userType, Integer categoria, String nombre, Integer precio, Integer stock, String shortDescription, String largeDescription, Integer status) {
        CrudRepository crudRepository = new CrudRepositoryImpl();
        return crudRepository.registrarNuevoProducto(idUser, userType, categoria, nombre,precio,stock,shortDescription,largeDescription,status);
    }

    @Override
    public boolean actualizarProducto(Integer idUser, Integer userType, Integer categoria, String nombre, Integer precio, Integer stock, String shortDescription, String largeDescription, Integer status, Integer idProducto) {
        CrudRepository crudRepository = new CrudRepositoryImpl();
        return crudRepository.actualizarProducto(idUser, userType, categoria, nombre,precio,stock,shortDescription,largeDescription,status,idProducto);
    }

    @Override
    public String obtenerNombreCategoria(Integer tipoProducto) {
        TipoProductoRepository tipoProductoRepository = new TipoProductoRepositoryImpl();
        return tipoProductoRepository.obtenerNombreCategoria(tipoProducto);
    }

    @Override
    public Integer obtenerNumeroCategorias() {
        TipoProductoRepository tipoProductoRepository = new TipoProductoRepositoryImpl();
        return tipoProductoRepository.obtenerNumeroCategorias();
    }

    @Override
    public Integer obtenerNumeroDeUsuariosRegistrados() {
        UsuarioRepository usuarioRepository = new UsuarioRepositoryImpl();
        return usuarioRepository.obtenerNumeroDeUsuariosRegistrados();
    }
}
