package org.tecsharp.apiservlet.webapp.headers.repositories.tipoproducto.impl;

import org.tecsharp.apiservlet.webapp.headers.repositories.tipoproducto.TipoProductoRepository;
import org.tecsharp.apiservlet.webapp.headers.utils.Constantes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TipoProductoRepositoryImpl implements TipoProductoRepository {


    @Override
    public String obtenerNombreCategoria(Integer tipoProducto) {

        String nombreCategoria = null;

        String query = "SELECT * FROM product_type WHERE id_product_type = ?";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, tipoProducto);

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                nombreCategoria = result.getString("name");

            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return nombreCategoria;
    }

    @Override
    public Integer obtenerNumeroCategorias() {

        Integer numCategorias = null;

        String query = "SELECT COUNT(id_product_type) AS numProductType FROM product_type";
        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                numCategorias = result.getInt("numProductType");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return numCategorias;
    }

}
