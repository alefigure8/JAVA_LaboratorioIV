package ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Archivo {
	
	private String ruta;
	
	//CONSTRUCTORES
	public Archivo() {
		ruta = "C:\\archivoSinttitulo.txt";
	}
	
	public Archivo(String ruta) {
		
		this.ruta = ruta;
	}
	
	//GETTER
	public String getRuta() {
		return ruta;
	}

	//SETTER
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	//METODOS
	public Boolean existeArchivo()
	{
		File archivo = new File(ruta); 
		return archivo.exists();
	}
	
	public ArrayList<String> leerArchivo(){
		ArrayList<String> lineas = new ArrayList<String>();
		
		try (FileReader fr = new FileReader(ruta);
			BufferedReader br = new BufferedReader(fr))
		{
			String linea;
			
			while((linea = br.readLine()) != null) {
				lineas.add(linea);
			}
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return lineas;
	}
	
	public Boolean escribirArchivo(String linea) {
		try(FileWriter fw = new FileWriter(ruta, true);
			BufferedWriter bw = new BufferedWriter(fw))
		{		
			bw.write(linea + "\n");
						
			return true;

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return false;
	}
}
