/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcelo
 */
public class UsuarioDAO {

    private Connection conexion;

    public UsuarioDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void agregarUsuario(Usuario usuario) {
        String sql = "insert into usuario(id_usuario, nombre, apellido, email, password, tipo) values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, usuario.getId());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getApellido());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getPassword());
            stmt.setInt(6, usuario.getTipo());

            int filasAfectadas = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar Usuario", e);

        }
    }

    //
    public void actualizarUsuario(Usuario usuario) {
        String sql = "update usuario set (id_usuario = ?, nombre = ?, apeliido = ?, email = ?, password = ?, tipo = ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, usuario.getId());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getApellido());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getPassword());
            stmt.setInt(6, usuario.getTipo());

            int filasAfectadas = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar Usuario ", e);

        }
    }

    public void eliminarUsuario(int idUsuario) {
        String sql = "delete from usuario where id_usuario = ? ";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);

            int filasAfectadas = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al elmininar el usuario", e);
        }
    }

    //
    public Usuario buscarProductoId(int id) {
        Usuario usuario = null;
        String sql = "select * from usuario where id_usuario = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                usuario = new Usuario();
                usuario.setId(rs.getString("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
                usuario.setTipo(rs.getInt("tipo"));

            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar usuario", e);
        }
        return usuario;
    }

    public List<Usuario> buscarUsuario() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "select * from usuario";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getString("id_usuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellido(rs.getString("apellido"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setTipo(rs.getInt("tipo"));

                    lista.add(usuario);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar Producto", e);
        }
        return lista;
    }

}
