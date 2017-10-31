package dominio;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private int id;
    private String idUsuario;
    private List<Producto> productos;

    public Carrito() {
        this.id = 0;
        this.idUsuario = "";
        this.productos = new ArrayList<>();
    }

    public Carrito(int id, String idUsuario, List<Producto> productos) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.productos = productos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

        Carrito carrito = (Carrito) o;

        return id == carrito.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "id=" + id +
                ", idUsuario='" + idUsuario + '\'' +
                ", productos=" + productos +
                '}';
    }
}
