package org.tecsharp.apiservlet.webapp.headers.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Producto {
    private Integer id;
    private String nombre;
    private Integer tipo;
    private Integer stock;
    private String descripcion;
    private Double precio;
    private String sky;
    private LocalDate fechaRegistro;
    private String imgLink;
    private String descripcionCorta;
    private Integer numItems;


    public Producto() {
    }


}
