package org.tecsharp.apiservlet.webapp.headers.services.crud;

public interface CrudService {

    boolean registrarNuevoProducto(Integer idUser, Integer userType, Integer categoria, String nombre, Integer precio, Integer stock, String shortDescription, String largeDescription, Integer status);


    }
