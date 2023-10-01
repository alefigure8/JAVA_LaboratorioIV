package presentacion.vista;

import javax.swing.JPanel;
import entidad.Persona;
import java.util.List;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class PanelEditarPersona extends JPanel implements IPanelList{
	
	private static final long serialVersionUID = 1L;
	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JList<Persona> list;
	private DefaultListModel<Persona> modelList;
	private JButton btnModificar;
	private List<Persona> personas;
	public PanelEditarPersona() {
		setLayout(null);
		
		list = new JList<Persona>();
		list.setBounds(27, 35, 375, 138);
		add(list);
		
		modelList = new DefaultListModel<Persona>();
		
		txtDni = new JTextField();
		txtDni.setBounds(27, 184, 86, 20);
		add(txtDni);
		txtDni.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(123, 184, 86, 20);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(219, 184, 86, 20);
		add(txtApellido);
		txtApellido.setColumns(10);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(315, 184, 89, 23);
		add(btnModificar);
	}
	
	//GETTERS & SETTERS
	public JTextField getTxtDni() {
		return txtDni;
	}

	public void setTxtDni(JTextField txtDni) {
		this.txtDni = txtDni;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtApellido() {
		return txtApellido;
	}

	public void setTxtApellido(JTextField txtApellido) {
		this.txtApellido = txtApellido;
	}

	public JButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(JButton btnModificar) {
		this.btnModificar = btnModificar;
	}
	

	public DefaultListModel<Persona> getModelList() {
		return modelList;
	}
	
	public JList<Persona> getList(){
		return list;
	}

	@Override
	public void setPersonaLista(List<Persona> personas){
		this.personas = personas;	
		actualizarLista();
	}
	
	public void actualizarLista() {
		if(!modelList.isEmpty()) {
			modelList.clear();
		}
		
		for(Persona persona:personas) {
			modelList.addElement(persona);
		}
		
		list.setModel(modelList);
	}
	
	public void limpiarFormulario() {
		txtDni.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
	
	}

}
