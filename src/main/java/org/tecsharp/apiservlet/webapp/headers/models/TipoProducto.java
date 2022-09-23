package org.tecsharp.apiservlet.webapp.headers.models;

import java.time.LocalDate;

public class TipoProducto {

    private Integer id;
    private String nombre;
    private String descripcion;
    private String linkPath;

    public TipoProducto() {
    }

    public String getLinkPath() {
        return linkPath;
    }

    public TipoProducto setLinkPath(String linkPath) {
        this.linkPath = linkPath;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public TipoProducto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoProducto setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public TipoProducto setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public TipoProducto(Integer id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }


}
