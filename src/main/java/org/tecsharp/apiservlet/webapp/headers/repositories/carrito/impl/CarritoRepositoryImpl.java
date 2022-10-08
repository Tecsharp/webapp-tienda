package org.tecsharp.apiservlet.webapp.headers.repositories.carrito.impl;

import org.tecsharp.apiservlet.webapp.headers.models.Carrito;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.models.TipoProducto;
import org.tecsharp.apiservlet.webapp.headers.models.Ventas;
import org.tecsharp.apiservlet.webapp.headers.repositories.carrito.CarritoRepository;
import org.tecsharp.apiservlet.webapp.headers.utils.Constantes;
import org.tecsharp.apiservlet.webapp.headers.utils.Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        String query = "SELECT *, t.name as categoria FROM products as p INNER JOIN cart as c ON  (c.id_product = p.id_product) INNER JOIN product_type as t ON (p.product_type = t.id_product_type) WHERE id_user = ?";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Producto producto = new Producto();
                TipoProducto tipo2 = new TipoProducto();

                Integer tipoId = result.getInt("product_type");
                tipo2.setId(tipoId);
                tipo2.setNombre(result.getString("categoria"));
                producto.setTipo(tipo2);

                producto.setId(result.getInt("id_product"));
                producto.setNombre(result.getString("name"));
                producto.setStock(result.getInt("stock"));
                producto.setPrecio(result.getInt("price"));
                producto.setDescripcion(result.getString("description"));
                producto.setDescripcionCorta(result.getString("short_description"));
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
    public boolean agregarProductoAlCarrito(Integer productoID, Integer idUser, Integer numItems, boolean productoDuplicado) {

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
            } catch (Exception e) {
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

    @Override
    public boolean comprarCarrito(List<Producto> productos, Integer idUser) {

        boolean ventaHecha = false;
        Integer productoStock = 0;
        for (Producto producto : productos) {
            productoStock = producto.getStock();
            if (productoStock == 0) {
                return false;
            }
        }

        Integer idSale = null;
        String query1 = "SELECT MAX(id_sale) FROM sales";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query1)) {

            ResultSet result = statement.executeQuery();
            while (result.next()) {

                idSale = result.getInt("MAX(id_sale)");

            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        idSale = idSale + 1;

        for (Producto producto : productos) {

            agregarVentasAProductos(producto.getId(), idUser, producto.getNumItems());

            LocalDateTime fecha = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fechaFormateada = fecha.format(myFormatObj);

            String query = "INSERT INTO sales VALUES (?,'Productos vendido',?,?,?,?,?,?,?,1);";
            boolean enCarrito = false;
            try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, idSale); // Correcto
                statement.setInt(2, idUser); // Correcto
                statement.setInt(3, producto.getId());
                statement.setInt(4, producto.getNumItems());
                statement.setInt(5, idUser);
                statement.setInt(6, idUser);
                statement.setString(7, fechaFormateada);
                statement.setString(8, fechaFormateada);
                statement.executeUpdate();

                ventaHecha = true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }

        limpiarCarrito(idUser);
        reducirStockPorCompra(productos);

        return ventaHecha;
    }

    @Override
    public boolean limpiarCarrito(Integer idUser) {


        String query = "DELETE FROM cart WHERE id_user = ?;";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idUser); // Correcto

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean reducirStockPorCompra(List<Producto> productos) {

        String productName = null;
        Integer productID = null;
        Integer stockRestante = null;

        for (Producto producto : productos) {

            productName = producto.getNombre();
            productID = producto.getId();

            // OBTIENE EL STOCK DEL PRODUCTO
            String query = "SELECT stock FROM products WHERE id_product = ?";
            try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, productID); // Correcto

                ResultSet result = statement.executeQuery();

                while (result.next()) {

                    stockRestante = result.getInt("stock");

                    // System.out.println(producto);
                }

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

            Integer items = producto.getNumItems();
            // ACTUALIZA EL STOCK
            String query2 = "UPDATE products SET stock = ? WHERE id_product = ?";
            try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
                 PreparedStatement statement = connection.prepareStatement(query2)) {

                statement.setInt(1, stockRestante - items);
                statement.setInt(2, productID); // Correcto
                statement.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }

        return true;
    }

    @Override
    public List<Ventas> getVentas(Integer userId) {

        List<Ventas> ventas = new ArrayList<>();

        String query = "SELECT * FROM sales INNER JOIN products ON products.id_product = sales.id_product WHERE id_user = ? ORDER BY id_sale DESC";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Ventas venta = new Ventas();
                Producto producto = new Producto();
                TipoProducto tipoProducto = new TipoProducto();

                venta.setIdSale(result.getInt("id_sale"));

                String nombreProducto = result.getString("name");
                producto.setNombre(nombreProducto);
                venta.setNameProduct(producto);

                Integer idProducto = result.getInt("id_product");
                producto.setId(idProducto);
                venta.setIdProduct(producto);

                Integer precio = result.getInt("price");
                producto.setPrecio(precio);
                venta.setPrice(producto);

                Integer tipo = result.getInt("product_type");
                tipoProducto.setId(tipo);
                venta.setProductType(tipoProducto);

                venta.setNumItems(result.getInt("num_items"));
                venta.setDateCreate(result.getDate("date_create"));
                ventas.add(venta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ventas;
    }

    @Override
    public boolean agregarVentasAProductos(Integer productoID, Integer idUser, Integer numItems) {
        Integer nuevoNumVentas = null;
        Integer ventasDeProducto = null;
        String query = "SELECT ventas FROM products WHERE id_product = ?";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, productoID);
            //statement.setInt(2, idUser);

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                ventasDeProducto = result.getInt("ventas");

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        nuevoNumVentas = ventasDeProducto + numItems;

        String query2 = "UPDATE products SET ventas = ? WHERE id_product = ?";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query2)) {

            // ResultSet result = statement.executeQuery();

            statement.setInt(1, nuevoNumVentas);
            statement.setInt(2, productoID);
            //statement.setInt(3, idUser);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return true;
    }

}

