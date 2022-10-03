package org.tecsharp.apiservlet.webapp.headers.services.crud.impl;

import org.tecsharp.apiservlet.webapp.headers.repositories.crud.CrudRepository;
import org.tecsharp.apiservlet.webapp.headers.repositories.crud.impl.CrudRepositoryImpl;
import org.tecsharp.apiservlet.webapp.headers.services.crud.CrudService;

public class CrudServiceImpl implements CrudService {
    @Override
    public boolean registrarNuevoProducto(Integer idUser, Integer userType, Integer categoria, String nombre, Integer precio, Integer stock, String shortDescription, String largeDescription, Integer status) {
        CrudRepository crudRepository = new CrudRepositoryImpl();


        return crudRepository.registrarNuevoProducto(idUser, userType, categoria, nombre,precio,stock,shortDescription,largeDescription,status);
    }
}
