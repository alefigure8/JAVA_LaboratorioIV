package dominio;

public class Persona {
    private String dni;
    private String nombre;
    private String apellido;

    // Constructor sin par�metros
    public Persona() {
        // Inicializa los atributos con valores por defecto o vac�os
        this.dni = "";
        this.nombre = "";
        this.apellido = "";
    }

    // Constructor con par�metros
    public Persona(String dni, String nombre, String apellido) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    // M�todos getter y setter para el atributo 'dni'
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    // M�todos getter y setter para el atributo 'nombre'
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // M�todos getter y setter para el atributo 'apellido'
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return  this.nombre + " " + this.apellido + " " + this.dni;
    }
}