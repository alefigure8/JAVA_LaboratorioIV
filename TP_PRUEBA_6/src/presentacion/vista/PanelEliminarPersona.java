package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import entidad.Persona;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;

public class PanelEliminarPersona extends JPanel implements IPanelList{
	public PanelEliminarPersona() {
		setLayout(null);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setBounds(124, 157, 46, 14);
		add(lblEliminar);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void setPersonaLista(List<Persona> personas){
		// TODO Auto-generated method stub
		
	}

}
