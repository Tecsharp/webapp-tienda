package org.tecsharp.apiservlet.webapp.headers.repositories.crud;

public interface CrudRepository {

    boolean registrarNuevoProducto(Integer idUser, Integer userType, Integer categoria,
                                   String nombre, Integer precio, Integer stock,
                                   String shortDescription, String largeDescription, Integer status);

    boolean actualizarProducto(Integer idUser, Integer userType, Integer categoria,
                                   String nombre, Integer precio, Integer stock,
                                   String shortDescription, String largeDescription, Integer status, Integer idProducto);

}
