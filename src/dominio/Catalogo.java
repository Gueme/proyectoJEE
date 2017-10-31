package dominio;

public class Catalogo {
    private int id;
    private String descripcion, idUsuario;

    public Catalogo() {
        this.id = 0;
        this.descripcion = "";
        this.idUsuario = "";
    }

    public Catalogo(int id, String descripcion, String idUsuario) {
        this.id = id;
        this.descripcion = descripcion;
        this.idUsuario = idUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Catalogo catalogo = (Catalogo) o;

        return id == catalogo.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Catalogo{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", idUsuario='" + idUsuario + '\'' +
                '}';
    }
}
