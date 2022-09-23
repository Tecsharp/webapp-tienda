package org.tecsharp.apiservlet.webapp.headers.models;

import lombok.Data;

import java.util.Date;

@Data
public class Carrito {

    private Integer idCart;
    private Integer userCreate;
    private Integer userUpdate;
    private Date dateCreate;
    private Date dateUpdate;
    private Usuario idUser;
    private Producto idProduct;
}
