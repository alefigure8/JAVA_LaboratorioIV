package main;


import negocio.IPersonaNegocio;
import presentacion.controlador.Controlador;
import presentacion.vista.VentanaPrincipal;
import negocioImpl.PersonaNegocioImpl;

public class Principal {
	public static void main(String[] args) {
		
		//INICIALIZA VENTANA PRINCIPAL
		VentanaPrincipal vista = new VentanaPrincipal();
		IPersonaNegocio negocio = new PersonaNegocioImpl();
		Controlador controlador = new Controlador(vista, negocio);
		controlador.inicializar();
		
	}
}
