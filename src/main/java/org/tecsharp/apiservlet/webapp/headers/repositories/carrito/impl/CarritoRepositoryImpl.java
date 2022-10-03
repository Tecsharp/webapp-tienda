package org.tecsharp.apiservlet.webapp.headers.repositories.carrito.impl;

import org.tecsharp.apiservlet.webapp.headers.models.Carrito;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.repositories.carrito.CarritoRepository;
import org.tecsharp.apiservlet.webapp.headers.services.carrito.CarritoService;
import org.tecsharp.apiservlet.webapp.headers.services.carrito.impl.CarritoServiceImpl;
import org.tecsharp.apiservlet.webapp.headers.utils.Constantes;
import org.tecsharp.apiservlet.webapp.headers.utils.Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarritoRepositoryImpl implements CarritoRepository {
    @Override
    public Integer obtenerCantidadItemsCarrito(Integer idUser) {

        Integer cantidadItemsCarrito = null;

        String query = "SELECT cart.num_items, COALESCE(SUM(num_items)) as itemscarrito from cart WHERE id_user = ?";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idUser);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                cantidadItemsCarrito = result.getInt("itemscarrito");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cantidadItemsCarrito;
    }

    @Override
    public Carrito obtenerCarrito(Integer idUser) {

        Carrito carritoDatos = new Carrito();

        String query = "SELECT products.price, cart.num_items, COALESCE(SUM(price*num_items)) as preciototal FROM products INNER JOIN cart ON cart.id_product = products.id_product WHERE id_user = ?";

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
                producto.setStatus(result.getInt("id_status"));
                String precioEnCadena = Utilidades.formatearPrecio((result.getInt("price")));
                producto.setPrecioFormateado(precioEnCadena);
                carrito.add(producto);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return carrito;
    }

    @Override
    public boolean agregarProductoAlCarrito(Integer productoID, Integer idUser,Integer numItems, boolean productoDuplicado) {

        if (productoDuplicado) {
            actualizarCarritoPorProductoDuplicado(productoID, idUser, numItems);
            return true;

        } else {

            DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.now();
            String ldtString = FOMATTER.format(localDateTime);

            String query = "INSERT INTO cart VALUES (0,1,1,?,?,1,?,?,?)";

            try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, ldtString);
                statement.setString(2, ldtString);
                statement.setInt(3, numItems);
                statement.setInt(4, idUser);
                statement.setInt(5, productoID);
                statement.executeUpdate();
            }

            catch (Exception e) {
                e.printStackTrace();
                return false;
            }

            //AGREGA LOS DATOS A productosoncarrito



            return true;
            //AGREGAR VALIDACION DE QUE SE AGREGO AL CARRITO
            //return serviceCarrito.validaProductoCarritoAgregado(enCarrito);
        }


    }


    @Override
    public void actualizarCarritoPorProductoDuplicado(Integer productoID, Integer idUser, Integer numItems) {

        Integer nuevoNumItems = null;
        Integer itemsEnCarrito = null;
        String query = "SELECT num_items FROM cart WHERE id_product = ? AND id_user = ?";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, productoID);
            statement.setInt(2, idUser);

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                itemsEnCarrito = result.getInt("num_items");

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        nuevoNumItems = itemsEnCarrito + numItems;

        String query2 = "UPDATE cart SET num_items = ? WHERE id_product = ? AND id_user = ?";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query2)) {

            // ResultSet result = statement.executeQuery();

            statement.setInt(1, nuevoNumItems);
            statement.setInt(2, productoID);
            statement.setInt(3, idUser);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public boolean eliminarPorductoDeCarrito(Integer idProducto, Integer idUser) {

        String query = "DELETE FROM cart WHERE id_product = ? AND id_user= ?";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idProducto);
            statement.setInt(2, idUser);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}

