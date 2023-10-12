package dominio;

public class Contratacion {
	private int idContratacion;
    private String nombreUsuario;
    private int idSeguro;
    private double costoContratacion;

    // Constructor sin argumentos (vacío)
    public Contratacion() {
        // Deja los atributos en sus valores predeterminados (puede ser 0 o 0.0, según el tipo de dato).
    }

    // Constructor con argumentos
    public Contratacion(int idContratacion, String nombreUsuario, int idSeguro, double costoContratacion) {
        this.idContratacion = idContratacion;
        this.nombreUsuario = nombreUsuario;
        this.idSeguro = idSeguro;
        this.costoContratacion = costoContratacion;
    }

    public int getIdContratacion() {
        return idContratacion;
    }

    public void setIdContratacion(int idContratacion) {
        this.idContratacion = idContratacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(int idSeguro) {
        this.idSeguro = idSeguro;
    }

    public double getCostoContratacion() {
        return costoContratacion;
    }

    public void setCostoContratacion(double costoContratacion) {
        this.costoContratacion = costoContratacion;
    }

    @Override
    public String toString() {
        return "Contratacion{" +
                "idContratacion=" + idContratacion +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", idSeguro=" + idSeguro +
                ", costoContratacion=" + costoContratacion +
                '}';
    }
}
