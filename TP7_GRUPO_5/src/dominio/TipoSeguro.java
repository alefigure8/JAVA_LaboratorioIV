package dominio;

public class TipoSeguro {
	private int idTipo;
    private String descripcion;

    // Constructor sin argumentos (vacío)
    public TipoSeguro() {
        // Deja los atributos en sus valores predeterminados (puede ser 0 o nulo, según el tipo de dato).
    }

    // Constructor con argumentos
    public TipoSeguro(int idTipo, String descripcion) {
        this.idTipo = idTipo;
        this.descripcion = descripcion;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoSeguro{" +
                "idTipo=" + idTipo +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
