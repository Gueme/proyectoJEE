package dominio;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
    private int id, total;
    private Timestamp fecha;
    private String estado, idUsuario;
    private List<Producto> productos;

    public Pedido() {
        this.id = 0;
        this.total = 0;
        this.fecha = new Timestamp(new Date().getTime());
        this.estado = "";
        this.idUsuario = "";
        this.productos = new ArrayList<>();
    }

    public Pedido(int id, int total, Timestamp fecha, String estado, String idUsuario, List<Producto> productos) {
        this.id = id;
        this.total = total;
        this.fecha = fecha;
        this.estado = estado;
        this.idUsuario = idUsuario;
        this.productos = productos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pedido pedido = (Pedido) o;

        return id == pedido.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", total=" + total +
                ", fecha=" + fecha +
                ", estado='" + estado + '\'' +
                ", idUsuario='" + idUsuario + '\'' +
                ", productos=" + productos +
                '}';
    }
}
