package pruebas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import daoImp.ClienteDaoImp;
import entidad.Cliente;
import entidad.Direccion;
import entidad.Localidad;
import entidad.Provincia;
import entidad.TipoAcceso;
import entidad.TipoDireccion;

public class PruebasClientes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//AGREGAR
		
		Cliente cliente = new Cliente();

		//  Usuario
		//cliente.setId(13);
		cliente.setNombre("Prueba10");
		cliente.setApellido("Pérez");
		cliente.setUsuario("usuarioPrueba");
		cliente.setContrasenia("contrasenia123");
		cliente.setTipoAcceso(TipoAcceso.Cliente);
		cliente.setFechaAlta(LocalDate.now()); // Fecha de alta como hoy

		// dirección del cliente
		Direccion direccion = new Direccion();
		direccion.setCalle("Calle Principal");
		direccion.setNumero(123);
		direccion.setTipoDireccion(TipoDireccion.Casa);
		direccion.setNumeroDepartamento("Apto 3B");
		direccion.setCodigoPostal(1000);
		

		// Localidad y Provincia
		Localidad localidad = new Localidad();
		localidad.setIdLocalidad(1); 
		Provincia provincia = new Provincia();
		provincia.setIdProvincia(1); 

		// Asignar la localidad y provincia a la dirección
		direccion.setLocalidad(localidad);
		direccion.setProvincia(provincia);
		
		
		cliente.setDireccion(direccion);

		//  adicional del cliente
		cliente.setDni(123456111);
		cliente.setCuil(987654321);
		cliente.setSexo("M");
		cliente.setNacionalidad("Argentina");
		cliente.setNacimiento(LocalDate.of(1990, 5, 15)); 
		cliente.setEmail("juan@example.com");
		cliente.setTelefono(123456789);

		ClienteDaoImp daoImp= new ClienteDaoImp();
		if (daoImp.insertar(cliente)) {
			System.out.println("Insertado");
		}else {
			System.out.println("No insertado");
		}
		
		//MODIFICAR
		
		/*
		Cliente cliente = new Cliente();

		// Configuración del Cliente
		cliente.setId(16);
		cliente.setNombre("Prueba123456");
		cliente.setApellido("Pérez");
		cliente.setUsuario("usuarioJuan2");
		cliente.setContrasenia("contrasenia123");
		cliente.setTipoAcceso(TipoAcceso.Cliente);
		cliente.setFechaAlta(LocalDate.now()); // Fecha de alta como hoy

		// Configuración de la dirección del cliente
		Direccion direccion = new Direccion();
		direccion.setId(8);
		direccion.setCalle("Calle Principal");
		direccion.setNumero(123);
		direccion.setTipoDireccion(TipoDireccion.Casa);
		direccion.setNumeroDepartamento("Apto 3B");
		direccion.setCodigoPostal(1000);
		

		// Crear una instancia de Localidad y Provincia
		Localidad localidad = new Localidad();
		localidad.setIdLocalidad(1); // 
		Provincia provincia = new Provincia();
		provincia.setIdProvincia(1); // 

		// Asignar la localidad y provincia a la dirección
		direccion.setLocalidad(localidad);
		direccion.setProvincia(provincia);
		
		
		cliente.setDireccion(direccion);

		// Configuración adicional del cliente
		cliente.setDni(123456789);
		cliente.setCuil(987654321);
		cliente.setSexo("M");
		cliente.setNacionalidad("Argentina");
		cliente.setNacimiento(LocalDate.of(1990, 5, 15)); 
		cliente.setEmail("juan22@example.com");
		cliente.setTelefono(123456789);

		ClienteDaoImp daoImp= new ClienteDaoImp();
		if (daoImp.editar(cliente)) {
			System.out.println("Editado");
		}else {
			System.out.println("No editado");
		}
		*/
		
		
		//LISTAR
		List<Cliente> clientes = new ArrayList<Cliente>();
		ClienteDaoImp daoImp2= new ClienteDaoImp();
		clientes=daoImp2.obtenerTodos();
		
		 for (Cliente cliente2 : clientes) {
		        System.out.println("ID: " + cliente.getId());
		        System.out.println("Nombre: " + cliente.getNombre());
		        System.out.println("Apellido: " + cliente.getApellido());
		        System.out.println("------------------------------------");
		    }
		
		
	}

}
