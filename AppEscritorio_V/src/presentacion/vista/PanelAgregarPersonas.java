package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import daoImpl.Conexion;
import entidad.Persona;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PanelAgregarPersonas extends JPanel {
	
	//private JFrame frmAgenda;
	private JTable tablaPersonas;
	private JButton btnBorrar;
	
	private DefaultTableModel modelPersonas;
	private String[] nombreColumnas = {"Nombre y apellido","Telefono"};
	private JTextField txtNombreApe;
	private JTextField txtTelefono;

	
	 public PanelAgregarPersonas() {
	
		super();
		initialize();
	}


	public JTextField getTxtNombreApe() {
		return txtNombreApe;
	}

	public void setTxtNombreApe(JTextField txtNombreApe) {
		this.txtNombreApe = txtNombreApe;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public void setTxtTelefono(JTextField txtTelefono) {
		this.txtTelefono = txtTelefono;
	}
	
	public JButton getBtnAgregar() {
		return btnAgregar;
	}


	public void setBtnAgregar(JButton btnAgregar) {
		this.btnAgregar = btnAgregar;
	}


	public void setBtnBorrar(JButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}

	private JButton btnAgregar;

	
	private void initialize() 
	{
	
		this.setBounds(100, 100, 514, 455);
		this.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(15, 171, 444, 227);
		this.add(panel);
		panel.setLayout(null);
		
		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(15, 30, 383, 126);
		panel.add(spPersonas);
		
		modelPersonas = new DefaultTableModel(null,nombreColumnas);
		tablaPersonas = new JTable(modelPersonas);
		
		tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaPersonas.getColumnModel().getColumn(0).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(1).setResizable(false);
		
		spPersonas.setViewportView(tablaPersonas);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(310, 187, 89, 23);
		panel.add(btnBorrar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(15, 37, 438, 118);
		this.add(panel_1);
		
		JLabel label = new JLabel("Nombre y apellido");
		label.setBounds(30, 16, 170, 14);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("Telefono");
		label_1.setBounds(63, 53, 113, 14);
		panel_1.add(label_1);
		
		txtNombreApe = new JTextField();
		txtNombreApe.setColumns(10);
		txtNombreApe.setBounds(204, 13, 164, 20);
		panel_1.add(txtNombreApe);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(204, 50, 164, 20);
		panel_1.add(txtTelefono);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(279, 74, 89, 23);
		panel_1.add(btnAgregar);
	}
	
	public void show()
	{
		this.setVisible(true);
	}
	
	public JButton getBtnBorrar() 
	{
		return btnBorrar;
	}
	
	public DefaultTableModel getModelPersonas() 
	{
		return modelPersonas;
	}
	
	public JTable getTablaPersonas()
	{
		return tablaPersonas;
	}

	public String[] getNombreColumnas() 
	{
		return nombreColumnas;
	}


	public void llenarTabla(List<Persona> personasEnTabla) {
		this.getModelPersonas().setRowCount(0); //Para vaciar la tabla
		this.getModelPersonas().setColumnCount(0);
		this.getModelPersonas().setColumnIdentifiers(this.getNombreColumnas());

		for (Persona p : personasEnTabla)
		{
			String nombre = p.getNombre();
			String tel = p.getTelefono();
			Object[] fila = {nombre, tel};
			this.getModelPersonas().addRow(fila);
		}
		
	}
	
	public void mostrarMensaje(String mensaje)
	{
		JOptionPane.showMessageDialog(null, mensaje);
	}
	
}
