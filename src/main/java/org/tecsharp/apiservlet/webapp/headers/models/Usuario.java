package org.tecsharp.apiservlet.webapp.headers.models;

import lombok.Data;

import java.util.Date;

@Data
public class Usuario {

    private Integer idUser;
    private String username;
    private String nameUser;
    private String lastNameUsr;
    private String password;
    private String email;
    private Integer userType;
    private String description;
    private Integer userCreate;
    private Integer userUpdate;
    private Date dateCreate;
    private Date dateUpdate;
    private Integer status;

}
