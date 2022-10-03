package org.tecsharp.apiservlet.webapp.headers.repositories.crud.impl;

import org.tecsharp.apiservlet.webapp.headers.repositories.crud.CrudRepository;
import org.tecsharp.apiservlet.webapp.headers.utils.Constantes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CrudRepositoryImpl implements CrudRepository {

    @Override
    public boolean registrarNuevoProducto(Integer idUser, Integer userType, Integer categoria, String nombre, Integer precio, Integer stock, String shortDescription, String largeDescription, Integer status) {

        DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String fecha = FOMATTER.format(localDateTime);

        String query = "INSERT INTO products VALUES (0,?,?,?,?,?,?,?,?,?,?,?,?);";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nombre);
            statement.setInt(2, stock);
            statement.setInt(3, precio);
            statement.setInt(4, categoria);
            statement.setInt(5, idUser); //USER CREATE
            statement.setInt(6, idUser); //USER UPDATE
            statement.setString (7, fecha); //DATE CREATE
            statement.setString (8, fecha); //DATE UPDATE
            statement.setInt(9, status); // ID STATUS
            statement.setString(10, "/webapp-tienda/assets/img/products/test.jpg"); // IMG LINK
            statement.setString(11, shortDescription);
            statement.setString(12, largeDescription);
            statement.executeUpdate();
        }

        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
