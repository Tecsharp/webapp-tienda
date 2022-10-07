package org.tecsharp.apiservlet.webapp.headers.repositories.producto.impl;

import org.tecsharp.apiservlet.webapp.headers.models.Producto;
import org.tecsharp.apiservlet.webapp.headers.models.TipoProducto;
import org.tecsharp.apiservlet.webapp.headers.repositories.producto.ProductoRepository;
import org.tecsharp.apiservlet.webapp.headers.utils.Constantes;
import org.tecsharp.apiservlet.webapp.headers.utils.Utilidades;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductoRepositoryJdbcImpl implements ProductoRepository<Producto> {

    private Connection conn;

    public ProductoRepositoryJdbcImpl(Connection conn) {

        this.conn = conn;
    }

    @Override
    public List<Producto> listar(TipoProducto tipo) throws SQLException {

        List<Producto> productos = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.*, t.name as categoria FROM products as p" +
                     " INNER JOIN product_type as t" +
                     " ON (p.product_type = t.id_product_type)")) {

            while (rs.next()) {
                Producto p = getProducto(rs, tipo);

                productos.add(p);
            }

        }

        return productos;
    }

    @Override
    public List<Producto> listarByTipo(Integer productoTipo, TipoProducto tipo) throws SQLException {

        List<Producto> tipoProductos = new ArrayList<>();

        String query = "SELECT *, pt.name as categoria FROM products as p INNER JOIN product_type as pt on p.product_type = pt.id_product_type WHERE p.product_type = ?";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productoTipo);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Producto p = getProducto(rs, tipo);

                tipoProductos.add(p);
            }

//            while (result.next()) {
//                Producto producto = new Producto();
//                producto.setId(result.getInt("id_product"));
//                producto.setNombre(result.getString("name"));
//                producto.setTipo(result.getInt("product_type"));
//                producto.setImgLink(result.getString("link"));
//                producto.setDescripcion(result.getString("description"));
//                producto.setDescripcionCorta(result.getString("short_description"));
//                producto.setStock(result.getInt("stock"));
//                producto.setPrecio(result.getInt("price"));
//                producto.setStatus(result.getInt("id_status"));
//                String precioFormateado = Utilidades.formatearPrecio(result.getInt("price"));
//                producto.setPrecioFormateado(precioFormateado);
//
//
//                tipoProductos.add(producto);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tipoProductos;
    }



    @Override
    public List<Producto> listarCarrusel(Integer productoTipo, TipoProducto tipo) throws SQLException {

        List<Producto> carrusel = new ArrayList<>();
        String query = "SELECT *, (pt.name) as categoria FROM products as p INNER JOIN product_type as pt on p.product_type = pt.id_product_type WHERE p.product_type = ? LIMIT 4";
        //String query = "SELECT * FROM products WHERE product_type= ? LIMIT 4";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tipo.getId());

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Producto p = getProducto(rs, tipo);

                carrusel.add(p);
            }

//            while (result.next()) {
//                Producto producto = new Producto();
//                producto.setId(result.getInt("id_product"));
//                producto.setNombre(result.getString("name"));
//                producto.setTipo(result.getInt("product_type"));
//                producto.setImgLink(result.getString("link"));
//                producto.setDescripcion(result.getString("description"));
//                producto.setDescripcionCorta(result.getString("short_description"));
//                //producto.setStock(result.getInt("stock"));
//                producto.setPrecio(result.getInt("price"));
//                //producto.setProductType(tipoProducto);
//                //producto.setDescription(result.getString("description"));
//                //producto.setDateCreate(result.getDate("date_Create"));
//                //producto.setDateUpdate(result.getDate("date_update"));
//                //producto.setStatus(result.getInt("id_status"));
//                carrusel.add(producto);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return carrusel;
    }

    @Override
    public List<Producto> listarProductoRandom(Integer limite) throws SQLException {
        List<Producto> productosRandom = new ArrayList<>();
        String query = "SELECT *, t.name as categoria FROM products as p INNER JOIN product_type as t ON (p.product_type = t.id_product_type) WHERE id_product  ORDER BY RAND() LIMIT ?";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, limite);

            ResultSet rs = statement.executeQuery();
            TipoProducto tipo = null;
            while (rs.next()) {
                Producto p = getProducto(rs, tipo);

                productosRandom.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productosRandom;
    }

    @Override
    public List<Producto> listarProductosPopulares(Integer limite) throws SQLException {
        List<Producto> productosPopulares = new ArrayList<>();
        String query = "SELECT *, t.name as categoria FROM products as p INNER JOIN product_type as t ON (p.product_type = t.id_product_type) WHERE id_product  ORDER BY ventas DESC LIMIT ?";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, limite);

            ResultSet rs = statement.executeQuery();
            TipoProducto tipo = null;
            while (rs.next()) {
                Producto p = getProducto(rs, tipo);

                productosPopulares.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productosPopulares;
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
    public Integer obtenerNumeroDeProductos() {
        Integer numProductos = null;

        String query = "SELECT COUNT(id_product) AS numProducts FROM products";
        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                numProductos = result.getInt("numProducts");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return numProductos;
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
    public Producto obtenerProductoPorId(Integer id, TipoProducto tipo) throws SQLException {
        Producto producto = null;

        //String query = "SELECT * FROM products WHERE id_product = ?";
        String query = "SELECT *, pt.name as categoria FROM products as p INNER JOIN product_type as pt on p.product_type = pt.id_product_type WHERE id_product = ?";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);


            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    producto = getProducto(rs, tipo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return producto;
    }

    @Override
    public Producto porID(Integer id, TipoProducto tipo) throws SQLException {

        Producto producto = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.*, t.name as categoria FROM products as p" +
                " INNER JOIN product_type as t" +
                " ON (p.product_type = t.id_product_type)" +
                " WHERE p.id_product = ?")) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = getProducto(rs, tipo);
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


    public Producto getProducto(ResultSet rs, TipoProducto tipo) throws SQLException {

        Producto p = new Producto();
        TipoProducto tipo2 = new TipoProducto();


        p.setId(rs.getInt("id_product"));
        p.setNombre(rs.getString("name"));
        p.setPrecio(rs.getInt("price"));

        Integer tipoId = rs.getInt("product_type");
        tipo2.setId(tipoId);
        tipo2.setNombre(rs.getString("categoria"));

        p.setTipo(tipo2);
        //p.setTipo(rs.getInt("product_type"));
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
