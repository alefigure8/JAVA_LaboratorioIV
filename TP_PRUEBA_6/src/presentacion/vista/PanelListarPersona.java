package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import entidad.Persona;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelListarPersona extends JPanel implements IPanelList{
	
	private static final long serialVersionUID = 1L;
	private JTable jtPersona;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;

	
	public PanelListarPersona() {
		setLayout(null);	
		tableModel = new DefaultTableModel();
		jtPersona = new JTable();
		scrollPane = new JScrollPane(jtPersona);
		scrollPane.setBounds(60, 40, 350, 170);
		add(scrollPane);
	}

	@Override
	public void setPersonaLista(List<Persona> personas){

	    //COLUMNAS
	    tableModel.addColumn("DNI");
	    tableModel.addColumn("NOMBRE");
	    tableModel.addColumn("APELLIDO");

	    //FILAS
	    for (Persona persona : personas) {
	        tableModel.addRow(new String[]{persona.getDni(), persona.getNombre(), persona.getApellido()});
	    }

	    //COLOCAR TABLEMODEL A JTABLE
	    jtPersona.setModel(tableModel);

	}
}
