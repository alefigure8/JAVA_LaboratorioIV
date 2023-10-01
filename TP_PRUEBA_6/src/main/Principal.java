package main;

import negocioImpl.PersonaNegocio;
import presentacion.controlador.Controlador;
import presentacion.vista.VentanaPrincipal;

public class Principal {
	public static void main(String[] args) {
		VentanaPrincipal vista = new VentanaPrincipal();
		PersonaNegocio negocio = new PersonaNegocio();
		Controlador controlador = new Controlador(vista, negocio);
		controlador.inicializar();
	}
}
