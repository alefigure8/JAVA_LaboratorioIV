package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import entidad.Persona;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class VentanaModificar extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JList<Persona> listPersonas;
	private JButton btnModificar;
	private DefaultListModel<Persona> listaPersonas;
	private JScrollPane scrollPane;

	public VentanaModificar() {
		setLayout(null);
		
		listPersonas = new JList<Persona>();
		scrollPane = new JScrollPane(listPersonas);
		scrollPane.setVisible(true);
		scrollPane.setBounds(12, 58, 426, 176);
		add(scrollPane);
		
		listaPersonas = new DefaultListModel<Persona>();
		
		JLabel lblSeleccioneLaPersona = new JLabel("Seleccione la persona que desea modificar");
		lblSeleccioneLaPersona.setBounds(12, 29, 282, 16);
		add(lblSeleccioneLaPersona);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(12, 247, 100, 22);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(119, 247, 100, 22);
		add(txtApellido);
		txtApellido.setColumns(10);
		
		txtDni = new JTextField();
		txtDni.setBounds(225, 247, 100, 22);
		add(txtDni);
		txtDni.setColumns(10);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(337, 247, 101, 25);
		add(btnModificar);

	}
	
	public void setDefaultListModel(DefaultListModel<Persona> listaPersonas) {
		this.listaPersonas = listaPersonas;
		listPersonas.setModel(this.listaPersonas);
	}
	
	public JList<Persona> getListPersona() {
		return listPersonas;
	}
	
	public JButton getBtnModificar() {
		return btnModificar;
	}
	
	public JTextField getTextFieldDni() {
		return txtDni;
	}
	
	public JTextField getTextFieldNombre() {
		return txtNombre;
	}
	
	public JTextField getTextFieldApellido() {
		return txtApellido;
	}
	
	public void Mostrarmensaje (String mensaje, boolean error) {
		
		if(!error){
			JOptionPane.showMessageDialog(null, mensaje, "Mensaje",JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, mensaje, "Error",JOptionPane.ERROR_MESSAGE);
		}
			
		
	}
}
