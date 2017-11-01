/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Producto;
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
public class ProductoDAO {

    private Connection conexion;

    public ProductoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void agregarProducto(Producto producto) {
        String sql = "insert into producto(id_producto, descripcion_producto, nombre_producto, precio, id_catalogo) values (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, producto.getId());
            stmt.setString(2, producto.getDescripcion());
            stmt.setString(3, producto.getNombre());
            stmt.setInt(4, producto.getPrecio());
            stmt.setInt(5, producto.getIdCatalogo());

            int filasAfectadas = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar Producto", e);
        }
    }

    //   
    public void actualizarProducto(Producto producto) {
        String sql = "update producto set (id_producto = ?, descripcion_producto = ?, nombre_producto = ?, precio = ?, id_catalogo = ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, producto.getId());
            stmt.setString(2, producto.getDescripcion());
            stmt.setString(3, producto.getNombre());
            stmt.setInt(4, producto.getPrecio());
            stmt.setInt(5, producto.getIdCatalogo());

            int filasAfectadas = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el Producto", e);
        }
    }

    //  
    public void eliminarProducto(int idProducto) {
        String sql = "delete from producto where id_producto = ? ";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idProducto);

            int filasAfectadas = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al elmininar el Producto", e);
        }
    }

    //
    public Producto buscarProductoId(int id) {
        Producto producto = null;
        String sql = "select * from producto where id_producto = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                producto = new Producto();
                producto.setId(rs.getInt("id_producto"));
                producto.setDescripcion(rs.getString("decripcion_producto"));
                producto.setNombre(rs.getString("nombre_producto"));
                producto.setPrecio(rs.getInt("precio"));
                producto.setIdCatalogo(rs.getInt("id_catalogo"));

            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar Producto", e);
        }
        return producto;
    }

    //
    public List<Producto> buscarProducto() {
        List<Producto> lista = new ArrayList<>();
        String sql = "select * from producto";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Producto producto = new Producto();
                    producto.setId(rs.getInt("id_producto"));
                    producto.setDescripcion(rs.getString("decripcion_producto"));
                    producto.setNombre(rs.getString("nombre_producto"));
                    producto.setPrecio(rs.getInt("precio"));
                    producto.setIdCatalogo(rs.getInt("id_catalogo"));
                    lista.add(producto);
                }
            }

       } catch (SQLException e) {
            throw new RuntimeException("Error al buscar Producto", e);
        }
        return lista;
    }
}
