package org.tecsharp.apiservlet.webapp.headers.repositories.carrito.impl;

import org.tecsharp.apiservlet.webapp.headers.repositories.carrito.CarritoRepository;
import org.tecsharp.apiservlet.webapp.headers.utils.Constantes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CarritoRepositoryImpl implements CarritoRepository {
    @Override
    public Integer obtenerCantidadItemsCarrito(Integer idUser) {

        Integer cantidadItemsCarrito = null;

        String query = "SELECT COUNT(num_items) from cart WHERE id_user = ?";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idUser);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                cantidadItemsCarrito = result.getInt("COUNT(num_items)");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cantidadItemsCarrito;
    }

}

