package org.tecsharp.apiservlet.webapp.headers.repositories.carrito.impl;

import org.tecsharp.apiservlet.webapp.headers.models.Carrito;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.repositories.carrito.CarritoRepository;
import org.tecsharp.apiservlet.webapp.headers.utils.Constantes;
import org.tecsharp.apiservlet.webapp.headers.utils.Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Carrito obtenerCarrito(Integer idUser) {

        Carrito carritoDatos = new Carrito();

        String query = "SELECT products.price, COALESCE(SUM(price)) as preciototal FROM products INNER JOIN cart ON cart.id_product = products.id_product WHERE id_user = ?";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idUser);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                carritoDatos.setPrecioTotal(result.getInt("preciototal"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return carritoDatos;
    }

    @Override
    public List<Producto> getCarrito(Integer userId) {
        List<Producto> carrito = new ArrayList<>();
        String query = "SELECT * FROM products INNER JOIN cart ON  cart.id_product = products.id_product WHERE id_user = ?";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Producto producto = new Producto();
                producto.setId(result.getInt("id_product"));
                producto.setNombre(result.getString("name"));
                producto.setStock(result.getInt("stock"));
                producto.setPrecio(result.getInt("price"));
                producto.setDescripcion(result.getString("description"));
                producto.setDescripcionCorta(result.getString("short_description"));
//              producto.setDateCreate(result.getDate("date_Create"));
//              producto.setDateUpdate(result.getDate("date_update"));
                producto.setNumItems(result.getInt("num_items"));
                producto.setImgLink(result.getString("link"));
//              producto.setStatus(result.getInt("id_status"));
                String precioEnCadena = Utilidades.formatearPrecio((result.getInt("price")));
                producto.setPrecioFormateado(precioEnCadena);
                carrito.add(producto);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return carrito;
    }

}

