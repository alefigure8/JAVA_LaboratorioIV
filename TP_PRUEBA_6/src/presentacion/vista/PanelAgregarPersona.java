package presentacion.vista;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entidad.Persona;

import java.awt.TextField;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class PanelAgregarPersona extends JPanel implements IPanelList{
	
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JButton btnAceptar;
	
	public PanelAgregarPersona() {
		setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(93, 61, 46, 14);
		add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(93, 98, 46, 14);
		add(lblApellido);
		
		JLabel lblDni = new JLabel("Dni");
		lblDni.setBounds(93, 135, 46, 14);
		add(lblDni);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(179, 58, 140, 17);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(179, 95, 140, 17);
		add(txtApellido);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(179, 132, 140, 17);
		add(txtDni);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(93, 173, 89, 23);
		add(btnAceptar);
	}
	

	public JButton getBtnAceptar() {
		return this.btnAceptar;
	}
	
	public JTextField getTxtDni() {
		return txtDni;
	}
	
	public JTextField getTxtNombre() {
		return txtNombre;
	}
	
	public JTextField getTxtApellido() {
		return txtApellido;
	}
	public Persona getPersona() {
		
		String dni = txtDni.getText();
		String nombre = txtNombre.getText();
		String apellido = txtApellido.getText();
		return new Persona(dni, nombre, apellido);
	}
	
	public void limpiarFormulario() {
		txtDni.setText("");
		txtNombre.setText("");
		txtApellido.setText("");

	}

	@Override
	public void setPersonaLista(List<Persona> personas) {
		
		
	}
	
}
