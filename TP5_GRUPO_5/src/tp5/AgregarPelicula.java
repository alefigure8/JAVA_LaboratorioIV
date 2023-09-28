package tp5;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;

public class AgregarPelicula extends JPanel implements IPanelList {
	
	private static final long serialVersionUID = 1L;
	private static DefaultListModel<Peliculas> listModel;
	private JTextField txtNombre;
	private JComboBox cbCategorias;
	private JButton btnAceptar;
	private JLabel lblMostrarId;
	
	public AgregarPelicula() {
		configuracion();
	}

	private void configuracion() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.WEST;
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.gridx = 2;
		gbc_lblId.gridy = 2;
		add(lblId, gbc_lblId);
		
		lblMostrarId = new JLabel(Integer.toString(Peliculas.getContador()));
		lblMostrarId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblMostrarId = new GridBagConstraints();
		gbc_lblMostrarId.anchor = GridBagConstraints.WEST;
		gbc_lblMostrarId.gridwidth = 4;
		gbc_lblMostrarId.insets = new Insets(0, 0, 5, 5);
		gbc_lblMostrarId.gridx = 5;
		gbc_lblMostrarId.gridy = 2;
		add(lblMostrarId, gbc_lblMostrarId);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 2;
		gbc_lblNombre.gridy = 4;
		add(lblNombre, gbc_lblNombre);
		
		txtNombre = new JTextField();
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.gridwidth = 4;
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.gridx = 5;
		gbc_txtNombre.gridy = 4;
		add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblCategoria = new JLabel("G\u00E9nero");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblCategoria = new GridBagConstraints();
		gbc_lblCategoria.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategoria.gridx = 2;
		gbc_lblCategoria.gridy = 6;
		add(lblCategoria, gbc_lblCategoria);
		
		cbCategorias = new JComboBox();
		cbCategorias.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_cbCategorias = new GridBagConstraints();
		gbc_cbCategorias.gridwidth = 4;
		gbc_cbCategorias.insets = new Insets(0, 0, 5, 5);
		gbc_cbCategorias.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbCategorias.gridx = 5;
		gbc_cbCategorias.gridy = 6;
		cargarCategorias();
		add(cbCategorias, gbc_cbCategorias);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (validarCampos())
					agregarPelicula();
				else
					JOptionPane.showMessageDialog(null, "Complete todos los campos");
			}
		});
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAceptar.gridwidth = 3;
		gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAceptar.gridx = 2;
		gbc_btnAceptar.gridy = 8;
		add(btnAceptar, gbc_btnAceptar);
	}

	//Metodo de Interface
	public void setDefaultListModelt(DefaultListModel<Peliculas> listModel) {
		this.listModel = listModel;
	}
	
	// private boolean validarCampos(){}
	
	
	// CARGAR CATEGORIAS EN COMBOBOX
	private void cargarCategorias() {
		cbCategorias.addItem("Seleccione un genero");
		cbCategorias.addItem(new Categorias("Terror").getGenero());
		cbCategorias.addItem(new Categorias("Acción").getGenero());
		cbCategorias.addItem(new Categorias("Suspenso").getGenero());
		cbCategorias.addItem(new Categorias("Romántica").getGenero());
	}
	
	// AGREGAR PELICULA AL LIST MODEL
	public void agregarPelicula() {
		listModel.addElement(nuevaPelicula());
		limpiarCampos();
	}
	
	// CREAR PELICULA
	public Peliculas nuevaPelicula() {
		Peliculas pelicula = new Peliculas();
		pelicula.setNombre(txtNombre.getText());
		//System.out.println(cbCategorias.getSelectedItem());
		//pelicula.setCategoria((Categorias)cbCategorias.getSelectedItem());
		pelicula.setCategoria(new Categorias((String)cbCategorias.getSelectedItem()));
		return pelicula;
	}
	
	// LIMPIAR CAMPOS
	public void limpiarCampos() {
		cbCategorias.setSelectedIndex(0);
		txtNombre.setText("");
		lblMostrarId.setText(Integer.toString(Peliculas.getContador()));
		//listarpeliculas();
	}
	
	// listar peliculas
	/*public void listarpeliculas() {
		for(int i=0; i < listModel.getSize(); i++) {
		 System.out.println(listModel.getElementAt(i).toString());	
		}
	}*/
	
public boolean validarCampos() {
		
		if(txtNombre.getText().isEmpty()) {
			
			return false;
		}
		
		
		if(cbCategorias.getSelectedItem() == "Seleccione un genero") { 
		
		return false;
		}
		return true;
	} 
}
