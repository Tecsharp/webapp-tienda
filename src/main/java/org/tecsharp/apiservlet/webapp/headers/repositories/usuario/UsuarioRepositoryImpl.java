package org.tecsharp.apiservlet.webapp.headers.repositories.usuario;

import org.tecsharp.apiservlet.webapp.headers.models.Usuario;
import org.tecsharp.apiservlet.webapp.headers.utils.Constantes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UsuarioRepositoryImpl implements UsuarioRepository{


    @Override
    public Usuario findByUsernameAndPassword(String username, String password) {
        Usuario usuario = null;
        String query = "SELECT * FROM users WHERE username = ? AND passwd = ?";


        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                usuario = new Usuario();
                usuario.setIdUser(result.getInt("id_user"));
                usuario.setNameUser(result.getString("name_usr"));
                usuario.setLastNameUsr(result.getString("lastname_usr"));
                usuario.setUserType(result.getInt("user_type"));
                usuario.setUsername(result.getString("username"));
                usuario.setPassword(result.getString("passwd"));
                usuario.setEmail(result.getString("emailusr"));
                usuario.setDateCreate(result.getDate("date_create"));
                usuario.setDateUpdate(result.getDate("date_update"));
                usuario.setStatus(result.getInt("id_status"));
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }

    @Override
    public Integer obtenerNumeroDeUsuariosRegistrados() {
        Integer numUsuariosRegistrados = null;

        String query = "SELECT COUNT(id_user) AS numUsers FROM users";
        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                numUsuariosRegistrados = result.getInt("numUsers");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return numUsuariosRegistrados;
    }

    @Override
    public boolean registrarUsuario(String nombre, String apellido, String email, String username, String password) {

        DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String fecha = FOMATTER.format(localDateTime);

        String query = "INSERT INTO users VALUES (0,?,?,?,?,?,?,?,?,?,?,?,?);";

        try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setString(3, email);
            statement.setString(4, username);
            statement.setString(5, password);
            statement.setInt(6, 1); //USERTYPE
            statement.setString(7, "usuario cliente"); //DESCRIPTION
            statement.setInt(8, 0); //USER CREATE
            statement.setInt(9, 0); //USER UPDATE
            statement.setString(10, fecha); //DATE CREATE
            statement.setString(11, fecha); //DATE UPDATE
            statement.setInt(12, 1); //ID STATUS
            statement.executeUpdate();
        }

        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
