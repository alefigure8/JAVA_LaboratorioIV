package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import entidad.Persona;
	import negocio.IPersonaNegocio;
import negocioImpl.PersonaNegocioImpl;
import presentacion.vista.VentanaAgregar;
import presentacion.vista.VentanaEliminar;
import presentacion.vista.VentanaListar;
import presentacion.vista.VentanaModificar;
import presentacion.vista.VentanaPrincipal;
	

public class Controlador implements ActionListener{

		private VentanaPrincipal ventanaPrincipal;
		private VentanaAgregar ventanaAgregar;
		private VentanaModificar ventanaModificar;
		private VentanaEliminar ventanaEliminar;
		private VentanaListar ventanaListar;
		private IPersonaNegocio pNeg;
		
		public Controlador(VentanaPrincipal vista, IPersonaNegocio pNeg)
		{
				//Guardo todas las instancias que recibo en el constructor
				this.ventanaPrincipal = vista;
		        this.pNeg = pNeg;

	        	//Instancio los paneles
				this.ventanaAgregar = new VentanaAgregar();
				this.ventanaModificar = new VentanaModificar();
				this.ventanaEliminar=new VentanaEliminar();
		        this.ventanaListar = new VentanaListar();
		        
				//Eventos menu del Frame principal llam;ado Ventana
				this.ventanaPrincipal.getMntmAgregar().addActionListener(a->EventoClickMenu_AbrirPanel_AgregarPersona(a));
				this.ventanaPrincipal.getMntmModificar().addActionListener(a->EventoClickMenu_AbrirPanel_ModificarPersona(a));
				this.ventanaPrincipal.getMntmEliminar().addActionListener(a->EventoClickMenu_AbrirPanel_EliminarPersona(a));
				this.ventanaPrincipal.getMntmListar().addActionListener(a->EventoClickMenu_AbrirPanel_ListarPersonas(a));
				
				//EVENTOS VENTANA AGREGAR
				this.ventanaAgregar.getBtnAceptar().addActionListener(a->EventoClick_VentanaAgregar_BtnAceptar(a));   
				// agrega las restricciones a los textfields de la ventana agregar
				restriccionesVentanaAgregar();
				
				//EVENTOS VENTANA MODIFICAR
				ventanaModificar.getListPersona().addListSelectionListener(e -> EventoListSelection_ListEditar(e));
				ventanaModificar.getBtnModificar().addActionListener(a-> EventoClick_ModificarPersona(a));
				ventanaModificar.getTextFieldNombre().addKeyListener(EventoKeyTyped_TextFieldNombre());
				ventanaModificar.getTextFieldApellido().addKeyListener(EventoKeyTyped_TextFieldApellido());
				
				//EVENTOS VENTANA ELIMINAR
				ventanaEliminar.getBtnEliminar().addActionListener(e -> EventoClick_EliminarPersona(e));
		
		}
		

		public void inicializar(){
			this.ventanaPrincipal.setVisible(true);
		}
		
		//EventoClickMenu abrir PanelAgregarPersonas
		public void  EventoClickMenu_AbrirPanel_AgregarPersona(ActionEvent a)
		{		
			iniciarPanel(ventanaAgregar);
		}

		public void EventoClickMenu_AbrirPanel_ListarPersonas(ActionEvent a) {
			
			actualizarTablePersonas(); 
			iniciarPanel(ventanaListar);
			
			
		}
		
		
		private void restriccionesVentanaAgregar() {
			
		        ventanaAgregar.getTxtDni().addKeyListener(new KeyAdapter() {
		            @Override
		            public void keyTyped(KeyEvent e) {
		                char c = e.getKeyChar();
		                if (!Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
		                    e.consume();
		                }
		            }
		        });
		        
		        ventanaAgregar.getTxtNombre().addKeyListener(new KeyAdapter() {
		            @Override
		            public void keyTyped(KeyEvent e) {
		                char c = e.getKeyChar();
		                if (!Character.isLetter(c) && c != KeyEvent.VK_SPACE) {
		                    e.consume();
		                }
		            }
		        });

		        ventanaAgregar.getTxtApellido().addKeyListener(new KeyAdapter() {
		            @Override
		            public void keyTyped(KeyEvent e) {
		                char c = e.getKeyChar();
		                if (!Character.isLetter(c) && c != KeyEvent.VK_SPACE) {
		                    e.consume();
		                }
		            }
		        });
		    }
			
		public void EventoClick_VentanaAgregar_BtnAceptar(ActionEvent a) {
			Persona persona = new Persona(ventanaAgregar.getTxtDni().getText(),ventanaAgregar.getTxtNombre().getText(),
					ventanaAgregar.getTxtApellido().getText());
			
			if(CamposCompletosAgregar()) {	
				
				
				if(validarDni(persona.getDni())) {
					try {
						if(pNeg.insert(persona)) {
							ventanaAgregar.Mostrarmensaje("La persona ha sido agregada");	
							limpiarCampos();
						}
						else {
							ventanaAgregar.Mostrarmensaje("Esta persona ya fue registrada ");
							limpiarCampos();
						}
					}
					catch (Exception e) { 
						ventanaAgregar.Mostrarmensaje("Error al intentar ingresar persona ");
						}
				}
				else {
					ventanaAgregar.Mostrarmensaje("El DNI debe tener una longitud de 7 y 8 caracteres ");
				}		
			
			}
			else{
			ventanaAgregar.Mostrarmensaje("Es necesario Completar todos los campos");
			}
		}
		
		//VENTANA AGREGAR
		public void limpiarCampos() {
			ventanaAgregar.getTxtDni().setText("");
	        ventanaAgregar.getTxtNombre().setText("");
	        ventanaAgregar.getTxtApellido().setText("");
		}

		public boolean CamposCompletosAgregar () {
			if(
				!ventanaAgregar.getTxtDni().getText().isEmpty() &&
				!ventanaAgregar.getTxtNombre().getText().isEmpty() &&
				!ventanaAgregar.getTxtApellido().getText().isEmpty()
			) {
				return true;
			}
			else {
				return false;
			}
		}
		
		// VENTANA MODIFICAR
		public void EventoClickMenu_AbrirPanel_ModificarPersona(ActionEvent a) {
			actualizarListaPersonas();
			iniciarPanel(ventanaModificar);
		}
		
		public void actualizarListaPersonas() {
			DefaultListModel<Persona> DefaultListAuxiliar = new DefaultListModel<Persona>();
						
			List<Persona> listaPersonasAuxiliar = new ArrayList<Persona>(); 
			listaPersonasAuxiliar = pNeg.readAll();
			
			for(Persona persona: listaPersonasAuxiliar) {
				DefaultListAuxiliar.addElement(persona);
			}
			
			//Pasamos el model List
			ventanaModificar.setDefaultListModel(DefaultListAuxiliar);
			ventanaEliminar.setDefaultListModel(DefaultListAuxiliar);
			
			//Vaciamos campos y restringimos modificaciones si no hay seleccion
			limpiarCamposVentanaModificar();

		}
		
		
		public void actualizarTablePersonas() {
			
			DefaultTableModel tablemodel = new DefaultTableModel();
			
			
			tablemodel.addColumn("DNI");
			tablemodel.addColumn("Nombre");
			tablemodel.addColumn("Apellido");
			

			List<Persona> listaPersonasAuxiliar = new ArrayList<Persona>(); 
			listaPersonasAuxiliar = pNeg.readAll();
			
			for(Persona persona: listaPersonasAuxiliar) {
								
				 Vector<String> row = new Vector<>();
		            row.add(persona.getDni());
		            row.add(persona.getNombre());   	           
		            row.add(persona.getApellido());
								
				tablemodel.addRow(row);
			}
			
			ventanaListar.setDefaultTableModel(tablemodel);
		    
		       
		}
		
		private void EventoClick_ModificarPersona(ActionEvent a) {
			
			if(!ventanaModificar.getListPersona().isSelectionEmpty()) {
				String nombre = ventanaModificar.getTextFieldNombre().getText().trim();
				String apellido = ventanaModificar.getTextFieldApellido().getText().trim();
				String dniSelect = ventanaModificar.getListPersona().getSelectedValue().getDni().trim();
				
				try {
					if(pNeg.update(new Persona(dniSelect, nombre, apellido))){
						ventanaModificar.Mostrarmensaje("Los datos se modificaron correctamente", false);
						actualizarListaPersonas();
					} else {
						ventanaModificar.Mostrarmensaje("Todos los campos son obligatorios", true);
					}
	
				} catch (Exception e) {
					ventanaModificar.Mostrarmensaje(e.getMessage(), true);
				}
			} else {
				ventanaModificar.Mostrarmensaje("Debe seleccionar una persona", true);
			}
				
		}

		private void EventoListSelection_ListEditar(ListSelectionEvent e) {
			
			if (!e.getValueIsAdjusting()) {
				
				//Validamos que haya una selecci�n en el listado
				if(!ventanaModificar.getListPersona().isSelectionEmpty()) {
					
					String dni = ventanaModificar.getListPersona().getSelectedValue().getDni();
					String nombre = ventanaModificar.getListPersona().getSelectedValue().getNombre();
					String apellido = ventanaModificar.getListPersona().getSelectedValue().getApellido();
					
					//Coloca valores de la lista en los text fields
					ventanaModificar.getTextFieldDni().setText(dni);
					ventanaModificar.getTextFieldNombre().setText(nombre);
					ventanaModificar.getTextFieldApellido().setText(apellido);
					
					//Evitar que se pueda modificar el dni
					ventanaModificar.getTextFieldNombre().setEditable(true);
					ventanaModificar.getTextFieldApellido().setEditable(true);
				}
	        }
		}
		
		//Evita que se pueda agregar n�meros al campo apellido
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

		//Evita que se pueda agregar n�meros al campo nombre
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
		
		private void limpiarCamposVentanaModificar() {
			//Limpiar campos
			ventanaModificar.getTextFieldNombre().setText("");
			ventanaModificar.getTextFieldApellido().setText("");
			ventanaModificar.getTextFieldDni().setText("");
			
			//restringir campos
			ventanaModificar.getTextFieldDni().setEditable(false);
			ventanaModificar.getTextFieldNombre().setEditable(false);
			ventanaModificar.getTextFieldApellido().setEditable(false);
		}

		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
		//VENTANA ELIMINAR
		public void EventoClickMenu_AbrirPanel_EliminarPersona(ActionEvent a) {
			actualizarListaPersonas();
			iniciarPanel(ventanaEliminar);
		}
           //EVENTO ELIMINAR PERSONA
            private void EventoClick_EliminarPersona(ActionEvent a) {
            	if(!ventanaEliminar.getjListPersonas().isSelectionEmpty()) {
			
			String dniSelect = ventanaEliminar.getjListPersonas().getSelectedValue().getDni();
			String nombreSelect = ventanaEliminar.getjListPersonas().getSelectedValue().getNombre();
			String apellidoSelect = ventanaEliminar.getjListPersonas().getSelectedValue().getApellido();
			if(pNeg.delete(new Persona(dniSelect, nombreSelect, apellidoSelect))) {
				ventanaEliminar.mostrarMensaje("La persona se elimino correctamente");
									
				//Reiniciar listado
				actualizarListaPersonas();
			}
			else {
				
				ventanaEliminar.mostrarMensaje("No se pudo eliminar a la persona");
			}
            	
            	}
            	else {
            		
            		ventanaEliminar.mostrarMensaje("Debe seleccionar una persona");
            	}
			
		}
		
     		//INICIAR PANEL
		public void iniciarPanel(JPanel panel) {
			ventanaPrincipal.getContentPane().removeAll();
			ventanaPrincipal.getContentPane().add(panel);
			ventanaPrincipal.getContentPane().repaint();
			ventanaPrincipal.getContentPane().revalidate();
			panel.setVisible(true);
		}
		
		//VALIDAR LONGITUD DNI
		private boolean validarDni(String dni) {
			// valido que tenga un largo entre 7 y 8 y que sol contenga valores numericos
			boolean soloNumeros=(dni.length()>6 && dni.length()<9 && dni.matches("^[0-9]+$"));
			return soloNumeros;
		}
		
}
