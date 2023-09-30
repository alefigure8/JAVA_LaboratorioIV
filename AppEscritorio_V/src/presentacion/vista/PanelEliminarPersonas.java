package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PanelEliminarPersonas extends JPanel {
	
	private JTextField txtIdEliminar;
	private JLabel lblEliminar;
	private JButton btnEliminar;
	/**
	 * Create the panel.
	 */
	public PanelEliminarPersonas() {
		setLayout(null);
		
		lblEliminar = new JLabel("Ingrese el ID de la persona a eliminar:");
		lblEliminar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEliminar.setBounds(51, 67, 323, 16);
		add(lblEliminar);
		
		txtIdEliminar = new JTextField();
		txtIdEliminar.setBounds(128, 106, 116, 22);
		add(txtIdEliminar);
		txtIdEliminar.setColumns(10);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(277, 105, 97, 25);
		add(btnEliminar);

	}
	public JTextField getTxtIdEliminar() {
		return txtIdEliminar;
	}
	public void setTxtIdEliminar(JTextField txtIdEliminar) {
		this.txtIdEliminar = txtIdEliminar;
	}
	public JLabel getLblEliminar() {
		return lblEliminar;
	}
	public void setLblEliminar(JLabel lblEliminar) {
		this.lblEliminar = lblEliminar;
	}
	public JButton getBtnEliminar() {
		return btnEliminar;
	}
	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}
	
	public void mostrarMensaje(String mensaje)
	{
		JOptionPane.showMessageDialog(null, mensaje);
	}
	
}
