package ejercicio1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.JOptionPane;

public class Principal {
	    public static void main(String[] args) {
        	/*String dni = JOptionPane.showInputDialog(null, "Ingrese el n�mero de DNI:");

	        try {
	            if (Dni.verificarDniInvalido(dni)) {
	            	System.out.println("DNI v�lido: " + dni);
	            }
	            
	        } catch (DniInvalido e) {
	            System.out.println("Error " + e.getMessage() + ": " + dni);
	        }*/
	        TreeSet <Persona> listaPersonas=new TreeSet<Persona>();
	        
	        Archivo archivo=new Archivo("Personas.txt");
	        if(archivo.existeArchivo()) {
	        	System.out.println("Existe el archivo");
	        	ArrayList<String> personas=archivo.leerArchivo();
	        	String [] data=new String[3];
	        	
	        		for(int i=0;i<personas.size();i++) {
	        			data=personas.get(i).split("-");
	        			//corroboramos que son 3 los datos 
        	
	        			if(data.length==3) {
	        				Persona persona=new Persona(data[0],data[1],data[2]);
	        				try {
	        					//verirficamos dni y agregamos al treeSet
	        					if(Dni.verificarDniInvalido(persona.getDni())){
	        						listaPersonas.add(persona);
	        					}
	        				}
	        				catch(DniInvalido e) {
	        					System.out.println("Dni inv�lido"+" "+persona.getDni());
	        				}
	        				//volvemos a poner en 0 el string
	        				data=null;
	        			}
	        		}
	        }
	        
	        else {
	        	System.out.println("El archivo no existe");
	        }
	        
	        
	        archivo.setRuta("Resultado.txt");             
	    	
	
	        
	        Iterator<Persona> it=listaPersonas.iterator();
	        while(it.hasNext()) {
	        	Persona p=it.next();
	         	System.out.println(p.toString());	       
	         	
	        	String[] datospersona = {p.getApellido(),p.getNombre(), p.getDni()};
	        	String linea = String.join("-", datospersona);
	        	archivo.escribirArchivo(linea);  	             	
	               
	    	}
	        
	        
	    }
	}

