package ejercicio1;

public class Profesor extends Empleado implements Comparable<Profesor>{

	private String cargo;
	private int antiguedadDocente;

	//CONSTRUCTORES
	public Profesor() {
		super();
		cargo = "sin cargo";
		antiguedadDocente = 0;
	}
	
	public Profesor(String nombre, int edad, String cargo, int antiguedad) {
		super(nombre, edad);
		this.cargo = cargo;
		this.antiguedadDocente = antiguedad;
	} 
	
	//GETTERS Y SETTERS
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int getAntiguedadDocente() {
		return antiguedadDocente;
	}

	public void setAntiguedadDocente(int antiguedadDocente) {
		this.antiguedadDocente = antiguedadDocente;
	}

	//METODOS	
	@Override
	public String toString() {
		return super.toString() + ", CARGO: "+ cargo + ", ANTIGUEDAD: " + antiguedadDocente;
	}
	
	
	
	

	@Override
	public int compareTo(Profesor profe) {
		// ordenamiento por antiguedad de mayor a menor
		if(profe.antiguedadDocente == this.antiguedadDocente) {
			
			int compararNombre = this.getNombre().compareToIgnoreCase(profe.getNombre());
			
			if(compararNombre == 0) {
				if(profe.getId() < this.getId()) {
					return -1;
				}
				else if(profe.getId() > this.getId()) {
					return -1;
				}
				else if(profe.getId() == this.getId()) {
					return 0;				
					}
			}
			return compararNombre;
		}
		else if(profe.antiguedadDocente < this.antiguedadDocente){
			return -1;
		}
		return 1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + antiguedadDocente;
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
		return result;
	}
	
	//Ejercicio 1 punto 7
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		if (antiguedadDocente != other.antiguedadDocente)
			return false;
		if (cargo == null) {
			if (other.cargo != null)
				return false;
		} else if (!cargo.equals(other.cargo))
			return false;
		
		if(getNombre()==null) {
			if(other.getNombre()!=null) {
				return false;
			}
		}
		else if(!getNombre().equals(other.getNombre())) {
			return false;
		}
		
		if(getEdad()!=other.getEdad()) {
			return false;
		}
		
		return true;
	}

	
	
	
	
	
	
}
