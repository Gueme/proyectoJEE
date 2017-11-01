/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Catalogo;
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
public class CatalogoDAO {

    private Connection conexion;

    public CatalogoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void agregarCatalogo(Catalogo catalogo) {
        String sql = "insert into catalogo(id_catalogo, descripcion_catalogo, id_usuario) values (?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, catalogo.getId());
            stmt.setString(2, catalogo.getDescripcion());
            stmt.setString(3, catalogo.getIdUsuario());

            int filasAfectadas = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar Catalogo", e);
        }
    }

    //
    public void actualizarCatalogo(Catalogo catalogo) {
        String sql = "update cataligo set (id_catalogo = ?, descripcion_catalogo = ?, id_usuario = ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, catalogo.getId());
            stmt.setString(2, catalogo.getDescripcion());
            stmt.setString(3, catalogo.getIdUsuario());

            int filasAfectadas = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el  Catalogo", e);
        }
    }

    //
    public void eliminarCatalogo(int idCatalogo) {
        String sql = "delete from catalogo where id_catalogo = ? ";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idCatalogo);

            int filasAfectadas = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al elmininar el Catalogo", e);
        }
    }

    //
    public Catalogo buscarCatalogoId(int id) {
        Catalogo catalogo = null;
        String sql = "select * from catalogo where id_catalogo = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                catalogo = new Catalogo();
                catalogo.setId(rs.getInt("id_catalogo"));
                catalogo.setDescripcion(rs.getString("descripcion_catalogo"));
                catalogo.setIdUsuario(rs.getString("id_usuario"));

            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar Catalogo", e);
        }
        return catalogo;
    }

    //
    public List<Catalogo> buscarCatalogo() {
        List<Catalogo> lista = new ArrayList<>();
        String sql = "select * from catalogo";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Catalogo catalogo = new Catalogo();
                    catalogo.setId(rs.getInt("id_catalogo"));
                    catalogo.setDescripcion(rs.getString("descripcion_catalogo"));
                    catalogo.setIdUsuario(rs.getString("id_usuario"));
                    lista.add(catalogo);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar Catalogo", e);
        }
        return lista;
    }
}
