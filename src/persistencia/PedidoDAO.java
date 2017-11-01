/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Pedido;
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
public class PedidoDAO {

    private Connection conexion;

    public PedidoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void agregarPedido(Pedido pedido) {
        String sql = "insert into pedido(id_pedido, fecha_pedido, estado, total, id_usuario) values (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, pedido.getId());
            stmt.setTimestamp(2, pedido.getFecha());
            stmt.setString(3, pedido.getEstado());
            stmt.setInt(4, pedido.getTotal());
            stmt.setString(5, pedido.getIdUsuario());

            int filasAfectadas = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar Pedido", e);
        }
    }

    //
    public void actualizarPedido(Pedido pedido) {
        String sql = "update pedido set (id_pedido = ?, fecha_pedido = ?, estado = ?, total = ?, id_usuario = ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, pedido.getId());
            stmt.setTimestamp(2, pedido.getFecha());
            stmt.setString(3, pedido.getEstado());
            stmt.setInt(4, pedido.getTotal());
            stmt.setString(5, pedido.getIdUsuario());

            int filasAfectadas = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar Pedido", e);
        }
    }

    //
    public void eliminarPedido(int idPedido) {
        String sql = "delete from pedido where id_pedido = ? ";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idPedido);

            int filasAfectadas = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al elmininar el Pedido", e);
        }
    }

    //
    public Pedido buscarPedidoId(int id) {
        Pedido pedido = null;
        String sql = "select * from pedido where id_pedido = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                pedido = new Pedido();
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setFecha(rs.getTimestamp("fecha_pedido"));
                pedido.setEstado(rs.getString("estado"));
                pedido.setTotal(rs.getInt("total"));
                pedido.setIdUsuario(rs.getString("id_usuario"));

            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar Pedido", e);
        }
        return pedido;
    }

    //
    public List<Pedido> buscarPedido() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "select * from pedido";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Pedido pedido = new Pedido();
                    pedido.setId(rs.getInt("id_pedido"));
                    pedido.setFecha(rs.getTimestamp("fecha_pedido"));
                    pedido.setEstado(rs.getString("estado"));
                    pedido.setTotal(rs.getInt("total"));
                    pedido.setIdUsuario(rs.getString("id_usuario"));

                    lista.add(pedido);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar Producto", e);
        }
        return lista;
    }

}
