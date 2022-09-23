package org.tecsharp.apiservlet.webapp.headers.repositories;

import org.tecsharp.apiservlet.webapp.headers.models.Usuario;
import org.tecsharp.apiservlet.webapp.headers.utils.Constantes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
