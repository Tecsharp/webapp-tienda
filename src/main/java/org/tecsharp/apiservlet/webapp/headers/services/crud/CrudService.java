package org.tecsharp.apiservlet.webapp.headers.services.crud;

public interface CrudService {

    boolean registrarNuevoProducto(Integer idUser, Integer userType, Integer categoria, String nombre, Integer precio, Integer stock, String shortDescription, String largeDescription, Integer status, String ubicacionImg);
    boolean actualizarProducto(Integer idUser, Integer userType, Integer categoria, String nombre, Integer precio, Integer stock, String shortDescription, String largeDescription, Integer status, Integer idProducto);
    String obtenerNombreCategoria(Integer tipoProducto);
    Integer obtenerNumeroCategorias();
    Integer obtenerNumeroDeUsuariosRegistrados();
    boolean enviaUbicacionImagen(String ubicacion, String nombreDeProducto);
}
