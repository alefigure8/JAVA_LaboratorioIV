package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import entidad.Persona;
import negocioImpl.PersonaNegocio;
import presentacion.vista.IPanelList;
import presentacion.vista.PanelAgregarPersona;
import presentacion.vista.PanelEditarPersona;
import presentacion.vista.PanelEliminarPersona;
import presentacion.vista.PanelListarPersona;
import presentacion.vista.VentanaPrincipal;

public class Controlador implements ActionListener{
	private VentanaPrincipal ventanaPrincipal;
	private PersonaNegocio personaNegocio;
	private PanelAgregarPersona panelAgregarPersona;
	private PanelEditarPersona panelEditarPersona;
	private PanelEliminarPersona panelEliminarPersona;
	private PanelListarPersona panelListarPersona;
	private List<Persona>personas;
	
	public Controlador(VentanaPrincipal ventanaPrincipal, PersonaNegocio personaNegocio) {
		this.ventanaPrincipal = ventanaPrincipal;
		this.personaNegocio = personaNegocio;
		
		//LISTAR PERSONAS
		listarPersonas();
		
		//INICIAMOS PANELES
		panelAgregarPersona = new PanelAgregarPersona();
		panelEditarPersona = new PanelEditarPersona();
		panelEliminarPersona = new PanelEliminarPersona();
		panelListarPersona = new PanelListarPersona();
		
		//EVENTOS MENU
		ventanaPrincipal.getMntmAgregar().addActionListener(a-> EventoClickMenu_AbrirPanel_AgregarPersona(a));
		ventanaPrincipal.getMntmEliminar().addActionListener(a-> EventoClickMenu_AbrirPanel_EliminarPersona(a));
		ventanaPrincipal.getMntmModificar().addActionListener(a-> EventoClickMenu_AbrirPanel_ModificarPersona(a));
		ventanaPrincipal.getMntmListar().addActionListener(a-> EventoClickMenu_AbrirPanel_ListarPersona(a));
		
		//EVENTOS AGREGAR
		panelAgregarPersona.getTxtDni().addKeyListener(EventoKeyTyped_TextFieldDni());
		panelAgregarPersona.getTxtNombre().addKeyListener(EventoKeyTyped_TextFieldNombre());
		panelAgregarPersona.getTxtApellido().addKeyListener(EventoKeyTyped_TextFieldApellido());
		panelAgregarPersona.getBtnAceptar().addActionListener(a -> EventoClickBoton_Aceptar(a));
		
		//EVENTOS EDITAR
		panelEditarPersona.getList().addListSelectionListener(e -> EventoListSelection_ListEditar(e));
		panelEditarPersona.getBtnModificar().addActionListener(a-> EventoClick_ModificarPersona(a));
		
		//EVENTOS BORRAR
		
	}

	private void EventoClick_ModificarPersona(ActionEvent a) {
		 String dni = panelEditarPersona.getTxtDni().getText();
         String nombre = panelEditarPersona.getTxtNombre().getText();
         String apellido = panelEditarPersona.getTxtApellido().getText();
         
         Persona aux = new Persona(dni, nombre, apellido);
         
         try {
        	 if(personaNegocio.update(aux)) {
        		 
        		 mostrarMensaje("Modificado", false);
        		 
        	 } else {
        		 mostrarMensaje("No se pudo modificar", true);
        	 }
		} catch (Exception e) {
			mostrarMensaje(e.getMessage(), true);
		}
         
         refrescarListaModificaciones();
	}
	

	private void EventoListSelection_ListEditar(ListSelectionEvent e) {
		
		if (!e.getValueIsAdjusting()) {
			
			//VALIDAR QUE LA SELCCION NO ESTE VACIA
			if(!panelEditarPersona.getList().isSelectionEmpty()) {
	            panelEditarPersona.getTxtDni().setText(panelEditarPersona.getList().getSelectedValue().getDni());
	            panelEditarPersona.getTxtDni().setEditable(false);
	            panelEditarPersona.getTxtNombre().setText(panelEditarPersona.getList().getSelectedValue().getNombre());
	            panelEditarPersona.getTxtApellido().setText(panelEditarPersona.getList().getSelectedValue().getApellido());
			}
        }
		
	}

	//EVENTOS BOTONES
	private void  EventoClickBoton_Aceptar(ActionEvent a) {
		Persona aux = panelAgregarPersona.getPersona();
		
		try {
			if(personaNegocio.insert(aux)) {
				mostrarMensaje("Se cargó con éxito", false);
			}
		} catch (Exception e) {
			mostrarMensaje(e.getMessage(), true);
		}	
		
		refrescarFormularioAgregar();
	}

	//EVENTOS MENU
	private void EventoClickMenu_AbrirPanel_ListarPersona(ActionEvent a) {
		iniciarPanel(panelListarPersona);
	}

	private void EventoClickMenu_AbrirPanel_ModificarPersona(ActionEvent a) {
		iniciarPanel(panelEditarPersona);
	}

	private void EventoClickMenu_AbrirPanel_EliminarPersona(ActionEvent a) {
		iniciarPanel(panelEliminarPersona);
	}

	private void EventoClickMenu_AbrirPanel_AgregarPersona(ActionEvent a) {
		iniciarPanel(panelAgregarPersona);
	}
	
	//EVENTOS TEXTFIELD
	private KeyListener EventoKeyTyped_TextFieldDni() {
		return new KeyAdapter() {			
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
		        if (!Character.isDigit(c)) {
		            e.consume();
		        }
			}
		};
		
	}
	
	private KeyListener EventoKeyTyped_TextFieldApellido() {
		return new KeyAdapter() {			
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
		        if (Character.isDigit(c)) {
		            e.consume();
		        }
			}
		};
	}

	private KeyListener EventoKeyTyped_TextFieldNombre() {
		return new KeyAdapter() {			
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
		        if (Character.isDigit(c)) {
		            e.consume();
		        }
			}
		};
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void inicializar()
	{
		this.ventanaPrincipal.setVisible(true);;
	}
	
	private void mostrarMensaje(String mensaje, Boolean error) {
		if(!error) {
			JOptionPane.showMessageDialog(null, mensaje, "EXITO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		JOptionPane.showMessageDialog(null, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
		
	}
	
	private void iniciarPanel(IPanelList panel){
		ventanaPrincipal.getContentPane().removeAll();
		panel.setPersonaLista(personas);
		ventanaPrincipal.getContentPane().add((JPanel)panel);
		ventanaPrincipal.getContentPane().repaint();
		ventanaPrincipal.getContentPane().revalidate();
	}
	
	public void listarPersonas() {
		
		try {
			 this.personas = personaNegocio.readAll();
			
		} catch (Exception e) {
			mostrarMensaje(e.getMessage(), true);
		}	
	}
	
	public void refrescarFormularioAgregar() {
		//limpiar formulario
		panelAgregarPersona.limpiarFormulario();
				
		//Listar Personas
		listarPersonas();
	}
	
	public void refrescarListaModificaciones() {
		//LIMPIAR FORMULARIO
		 panelEditarPersona.limpiarFormulario();
		 
		 //ACTUALIZAR LISTA
		 listarPersonas();
		 
		 //PASAR NUEVA LISTA
		 panelEditarPersona.setPersonaLista(personas);
	}
}
