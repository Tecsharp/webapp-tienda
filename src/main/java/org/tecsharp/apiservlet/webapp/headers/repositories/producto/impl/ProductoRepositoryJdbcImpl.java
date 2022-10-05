package org.tecsharp.apiservlet.webapp.headers.repositories.producto.impl;

import org.tecsharp.apiservlet.webapp.headers.models.Carrito;
import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.models.TipoProducto;
import org.tecsharp.apiservlet.webapp.headers.repositories.producto.ProductoRepository;
import org.tecsharp.apiservlet.webapp.headers.utils.Constantes;
import org.tecsharp.apiservlet.webapp.headers.utils.Utilidades;

import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ProductoRepositoryJdbcImpl implements ProductoRepository<Producto> {

    private Connection conn;

    public ProductoRepositoryJdbcImpl(Connection conn) {

        this.conn = conn;
    }

    @Override
    public List<Producto> listar() throws SQLException {

        List<Producto> productos = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.*, t.name as categoria FROM products as p" +
                     " INNER JOIN product_type as t" +
                     " ON (p.product_type = t.id_product_type)")) {

            while (rs.next()) {
                Producto p = getProducto(rs);

                productos.add(p);
            }

        }

        return productos;
    }

    @Override
    public List<Producto> listarByTipo(Integer productoTipo) throws SQLException {

        List<Producto> tipoProductos = new ArrayList<>();

        String query = "SELECT * FROM products WHERE product_type = ?";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productoTipo);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Producto producto = new Producto();
                producto.setId(result.getInt("id_product"));
                producto.setNombre(result.getString("name"));
                producto.setTipo(result.getInt("product_type"));
                producto.setImgLink(result.getString("link"));
                producto.setDescripcion(result.getString("description"));
                producto.setDescripcionCorta(result.getString("short_description"));
                producto.setStock(result.getInt("stock"));
                producto.setPrecio(result.getInt("price"));
                producto.setStatus(result.getInt("id_status"));
                String precioFormateado = Utilidades.formatearPrecio(result.getInt("price"));
                producto.setPrecioFormateado(precioFormateado);

                //producto.setProductType(tipoProducto);
                //producto.setDescription(result.getString("description"));
                //producto.setDateCreate(result.getDate("date_Create"));
                //producto.setDateUpdate(result.getDate("date_update"));
                //producto.setStatus(result.getInt("id_status"));
                tipoProductos.add(producto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tipoProductos;
    }



    @Override
    public List<Producto> listarCarrusel(Integer productoTipo) throws SQLException {

        List<Producto> carrusel = new ArrayList<>();

        String query = "SELECT * FROM products WHERE product_type= ? LIMIT 4";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productoTipo);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Producto producto = new Producto();
                producto.setId(result.getInt("id_product"));
                producto.setNombre(result.getString("name"));
                producto.setTipo(result.getInt("product_type"));
                producto.setImgLink(result.getString("link"));
                producto.setDescripcion(result.getString("description"));
                producto.setDescripcionCorta(result.getString("short_description"));
                //producto.setStock(result.getInt("stock"));
                producto.setPrecio(result.getInt("price"));
                //producto.setProductType(tipoProducto);
                //producto.setDescription(result.getString("description"));
                //producto.setDateCreate(result.getDate("date_Create"));
                //producto.setDateUpdate(result.getDate("date_update"));
                //producto.setStatus(result.getInt("id_status"));
                carrusel.add(producto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return carrusel;
    }







    @Override
    public List<Producto> obtenerTodosLosProductosPorCategoria(Integer categoria) {
        List<Producto> todosProductos = new ArrayList<>();
        String query = "SELECT * FROM products WHERE product_type = ?";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, categoria);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Producto producto = new Producto();
                producto.setId(result.getInt("id_product"));
                producto.setNombre(result.getString("name"));
                producto.setStock(result.getInt("stock"));
                producto.setPrecio(result.getInt("price"));
                producto.setDescripcion(result.getString("description"));
                producto.setDescripcionCorta(result.getString("short_description"));
//                producto.setDateCreate(result.getDate("date_Create"));
//                producto.setDateUpdate(result.getDate("date_update"));
                producto.setNumItems(result.getInt("num_items"));
//                producto.setStatus(result.getInt("id_status"));
                todosProductos.add(producto);

                // System.out.println(producto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return todosProductos;
    }

    @Override
    public boolean eliminarProductoPorId(Integer idProducto) {

            String query = "DELETE FROM products WHERE id_product= ?";
            try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, idProducto);
                statement.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
    }

    @Override
    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> todosProductos = new ArrayList<>();
        String query = "SELECT * FROM products";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Producto producto = new Producto();
                producto.setId(result.getInt("id_product"));
                producto.setNombre(result.getString("name"));
                producto.setStock(result.getInt("stock"));
                producto.setPrecio(result.getInt("price"));
                producto.setDescripcion(result.getString("description"));
                producto.setDescripcionCorta(result.getString("short_description"));
//                producto.setDateCreate(result.getDate("date_Create"));
//                producto.setDateUpdate(result.getDate("date_update"));
                producto.setNumItems(result.getInt("num_items"));
//                producto.setStatus(result.getInt("id_status"));
                todosProductos.add(producto);

                // System.out.println(producto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return todosProductos;
    }

    @Override
    public List<TipoProducto> listarTipoProducto() throws SQLException {

        List<TipoProducto> tipoProductos = new ArrayList<>();

        String query = "SELECT * FROM product_type";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                TipoProducto tp = new TipoProducto();
                tp.setId(result.getInt("id_product_type"));
                tp.setNombre(result.getString("name"));
                tp.setDescripcion(result.getString("description"));

                tp.setLinkPath(result.getString("link"));
                tipoProductos.add(tp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tipoProductos;
    }

    @Override
    public Producto obtenerProductoPorId(Integer id) throws SQLException {
        Producto producto = null;

        String query = "SELECT * FROM products WHERE id_product = ?";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);


            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    producto = getProducto(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return producto;
    }

    @Override
    public Producto porID(Integer id) throws SQLException {

        Producto producto = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.*, t.name as categoria FROM products as p" +
                " INNER JOIN product_type as t" +
                " ON (p.product_type = t.id_product_type)" +
                " WHERE p.id_product = ?")) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = getProducto(rs);
                }
            }
        }

        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {

    }

    @Override
    public void eliminar(Integer id) throws SQLException {

    }


    public Producto getProducto(ResultSet rs) throws SQLException {

        Producto p = new Producto();
        p.setId(rs.getInt("id_product"));
        p.setNombre(rs.getString("name"));
        p.setPrecio(rs.getInt("price"));
        p.setTipo(rs.getInt("product_type"));
        p.setDescripcion(rs.getString("description"));
        p.setDescripcionCorta(rs.getString("short_description"));
        p.setStatus(rs.getInt("id_status"));
        p.setImgLink(rs.getString("link"));
        p.setStock(rs.getInt("stock"));
        String precioEnCadena = Utilidades.formatearPrecio((rs.getInt("price")));
        p.setPrecioFormateado(precioEnCadena);
        return p;
    }
}
