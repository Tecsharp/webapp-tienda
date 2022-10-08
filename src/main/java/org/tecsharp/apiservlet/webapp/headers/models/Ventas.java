package org.tecsharp.apiservlet.webapp.headers.models;

import lombok.Data;

import java.util.Date;

@Data
public class Ventas {

    private Producto nameProduct;
    private Integer idSale;
    private Usuario idUser;
    private Producto idProduct;
    private Producto price;
    private TipoProducto productType;
    private Integer numItems;
    private String description;
    private Integer userCreate;
    private Integer userUpdate;
    private Date dateCreate;
    private Date dateUpdate;
    private Integer status;

}