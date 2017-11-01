/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Carrito;
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
public class CarritoDAO {

    private Connection conexion;

    public CarritoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void agregarCarrito(Carrito carrito) {
        String sql = "insert into carrito(id_carrito, id_usuario) values (?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, carrito.getId());
            stmt.setString(2, carrito.getIdUsuario());

            int filasAfectadas = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar Carrito", e);
        }
    }

    //
    public void actualizarCarrito(Carrito carrito) {
        String sql = "update carrito set (id_carrito = ?, id_usuario = ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, carrito.getId());
            stmt.setString(2, carrito.getIdUsuario());

            int filasAfectadas = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar Carrito", e);
        }
    }

    //
    public Carrito buscarCarritoId(int id) {
        Carrito carrito = null;
        String sql = "select * from carrito where id_carrito = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                carrito = new Carrito();
                carrito.setId(rs.getInt("id_carrito"));
                carrito.setIdUsuario(rs.getString("id_usuario"));

            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar Carrito", e);
        }
        return carrito;
    }

    //
    public List<Carrito> buscarCatalogo() {
        List<Carrito> lista = new ArrayList<>();
        String sql = "select * from carrito";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Carrito carrito = new Carrito();
                    carrito.setId(rs.getInt("id_carrito"));
                    carrito.setIdUsuario(rs.getString("id_usuario"));
                    lista.add(carrito);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar Carrito", e);
        }
        return lista;
    }

}
