package presentacion.vista;

import javax.swing.ListSelectionModel;

import entidad.Persona;
import java.awt.GridBagLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class VentanaEliminar extends JPanel {
	
	private JButton btnEliminar;
	private JList <Persona>jListPersonas;
	
	private DefaultListModel<Persona>listModel;
	
	public VentanaEliminar() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{98, 192, 292, 308, -86, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 167, 17, 0, 165, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Eliminar usuarios");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		
		jListPersonas = new JList<Persona>();
		GridBagConstraints gbc_listPersonas = new GridBagConstraints();
		gbc_listPersonas.insets = new Insets(0, 0, 5, 5);
		gbc_listPersonas.fill = GridBagConstraints.BOTH;
		gbc_listPersonas.gridx = 2;
		gbc_listPersonas.gridy = 2;
		add(jListPersonas, gbc_listPersonas);
		
		btnEliminar = new JButton("Eliminar");
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.fill = GridBagConstraints.BOTH;
		gbc_btnEliminar.insets = new Insets(0, 0, 5, 5);
		gbc_btnEliminar.gridx = 2;
		gbc_btnEliminar.gridy = 4;
		add(btnEliminar, gbc_btnEliminar);
	}
	
	
	
	
	//Gets y sets
	public JList<Persona> getjListPersonas() {
		return jListPersonas;
	}

	public void setjListPersonas(JList<Persona> jListPersonas) {
		this.jListPersonas = jListPersonas;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	

	public void setDefaultListModel(DefaultListModel<Persona> listModel) {
		//VentanaEliminar.listModel = listModel;
		this.jListPersonas.setModel(listModel);
	}
	
	
	//Mostrar mensaje
		public void mostrarMensaje(String mensaje) {
			JOptionPane.showMessageDialog(null, mensaje);
		}
	
}
