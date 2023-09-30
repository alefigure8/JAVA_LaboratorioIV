package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entidad.Persona;
import negocio.PersonaNegocio;
import presentacion.vista.PanelEliminarPersonas;
import presentacion.vista.VentanaPrincipal;
import presentacion.vista.PanelAgregarPersonas;


public class Controlador implements ActionListener {

	private VentanaPrincipal ventanaPrincipal;
	private PanelAgregarPersonas pnlIngresoPersonas;
	private PanelEliminarPersonas pnlEliminarPersonas;
	private PersonaNegocio pNeg;
	private ArrayList<Persona> personasEnTabla;
	
	//Constructor
	public Controlador(VentanaPrincipal vista, PersonaNegocio pNeg)
	{
		//Guardo todas las instancias que recibo en el constructor
		this.ventanaPrincipal = vista;
		this.pNeg = pNeg;
		
		//Instancio los paneles
		this.pnlIngresoPersonas = new PanelAgregarPersonas();
		this.pnlEliminarPersonas = new PanelEliminarPersonas();
		
		//Enlazo todos los eventos
		
		//Eventos menu del Frame principal llamado Ventana
		this.ventanaPrincipal.getMenuAgregar().addActionListener(a->EventoClickMenu_AbrirPanel_AgregarPersona(a));
		this.ventanaPrincipal.getMenuEliminar().addActionListener(a->EventoClickMenu_AbrirPanel_EliminarPersona(a));

		//Eventos PanelAgregarPersonas
		 this.pnlIngresoPersonas.getBtnAgregar().addActionListener(a->EventoClickBoton_AgregarPesona_PanelAgregarPersonas(a));
		 this.pnlIngresoPersonas.getBtnBorrar().addActionListener(s->EventoClickBoton_BorrarPesona_PanelAgregarPersonas(s));
			
		//Eventos PanelEliminarPersonas
		 this.pnlEliminarPersonas.getBtnEliminar().addActionListener(s->EventoClickBoton_BorrarPesona_PanelEliminarPersonas(s));
		 
		}
	
	//EventoClickMenu abrir PanelAgregarPersonas
	public void  EventoClickMenu_AbrirPanel_AgregarPersona(ActionEvent a)
	{		
		ventanaPrincipal.getContentPane().removeAll();
		ventanaPrincipal.getContentPane().add(pnlIngresoPersonas);
		ventanaPrincipal.getContentPane().repaint();
		ventanaPrincipal.getContentPane().revalidate();
	}
	
	//EventoClickMenu abrir PanelEliminarPersonas
	public void EventoClickMenu_AbrirPanel_EliminarPersona(ActionEvent a)
	{		
		ventanaPrincipal.getContentPane().removeAll();
		ventanaPrincipal.getContentPane().add(pnlEliminarPersonas);
		ventanaPrincipal.getContentPane().repaint();
		ventanaPrincipal.getContentPane().revalidate();
	}

	//EventoClickBoton agregar persona en PanelAgregarPersonas
	private void EventoClickBoton_AgregarPesona_PanelAgregarPersonas(ActionEvent a) {
		
		String nombre = this.pnlIngresoPersonas.getTxtNombreApe().getText();
		String tel = this.pnlIngresoPersonas.getTxtTelefono().getText();
		Persona nuevaPersona = new Persona(0, nombre, tel);
		
		boolean estado = pNeg.insert(nuevaPersona);
		String mensaje;
		if(estado==true)
		{
			mensaje="Persona agregada con exito";
			this.pnlIngresoPersonas.getTxtNombreApe().setText("");
			this.pnlIngresoPersonas.getTxtTelefono().setText("");
		}
		else
			mensaje="Persona no agregada, complete todos los campos";
		
		this.pnlIngresoPersonas.mostrarMensaje(mensaje);
		this.refrescarTabla();
	
	}

	//EventoClickBoton borrar persona en PanelAgregarPersonas
	public void EventoClickBoton_BorrarPesona_PanelAgregarPersonas(ActionEvent s)
	{
		boolean estado=false;
		int[] filasSeleccionadas = this.pnlIngresoPersonas.getTablaPersonas().getSelectedRows();
		for (int fila : filasSeleccionadas)
		{
			estado = pNeg.delete(this.personasEnTabla.get(fila));
			if(estado==true)
			{
				String mensaje="Persona eliminada con exito";
				this.pnlIngresoPersonas.mostrarMensaje(mensaje);
			}
		}
		this.refrescarTabla();
	}
	
	
	//EventoClickBoton borrar persona en PanelEliminarPersonas
	public void EventoClickBoton_BorrarPesona_PanelEliminarPersonas(ActionEvent s)
		{
			String id_eliminar = this.pnlEliminarPersonas.getTxtIdEliminar().getText();
			Persona persona = new Persona();
			persona.setIdPersona(Integer.parseInt(id_eliminar));
			
			boolean estado=false;
			String mensaje;
			estado = pNeg.delete(persona);
			if(estado==true)
			{
				mensaje="Persona eliminada con exito";	
			}
			else
			{
				 mensaje="Id inexistente";
			}
			this.pnlEliminarPersonas.getTxtIdEliminar().setText("");
			this.pnlEliminarPersonas.mostrarMensaje(mensaje);
			
		}
		
	
	private void refrescarTabla()
	{
		this.personasEnTabla = (ArrayList<Persona>) pNeg.readAll();
		this.pnlIngresoPersonas.llenarTabla(this.personasEnTabla);
	}



	
	public void inicializar()
	{
		this.ventanaPrincipal.setVisible(true);;
	}
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
