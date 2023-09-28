package ejercicio;
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

public class AgregarPelicula extends JPanel implements Panel {
	private static final long serialVersionUID = 1L;
	private JLabel lblId;
	private JLabel lblNombre;
	private JLabel lblGenero;
	private JLabel lblNumeroid;
	private DefaultListModel<Pelicula> listModel;
	private JTextField txtNombre;
	private JComboBox<String> cbCategoria;
	private JButton btnAgregar;
	
	public AgregarPelicula() {
		configuracion();
		labels();
		comboBox();
		textFields();
		botones();
	}

	private void configuracion() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
	}

	private void labels() {
		lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblId.setBounds(46, 29, 46, 14);
		add(lblId);

		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombre.setBounds(46, 68, 62, 14);
		add(lblNombre);

		lblGenero = new JLabel("Genero");
		lblGenero.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGenero.setBounds(46, 103, 46, 14);
		add(lblGenero);

		lblNumeroid = new JLabel("");
		lblNumeroid.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNumeroid.setBounds(195, 29, 46, 14);
		lblNumeroid.setText(String.valueOf(Pelicula.getIdPelicula()));
		add(lblNumeroid);
						
	}
	
	public void comboBox() {
		cbCategoria = new JComboBox<String>();
		cbCategoria.setBounds(195, 101, 120, 20);
		cbCategoria.addItem("Seleccione un genero");
		cbCategoria.addItem("Terror");
		cbCategoria.addItem("Accion");
		cbCategoria.addItem("Suspenso");
		cbCategoria.addItem("Romantica");
		add(cbCategoria);
	}
	
	public void textFields() {
		txtNombre = new JTextField();
		txtNombre.setBounds(195, 66, 120, 20);
		add(txtNombre);
		txtNombre.setColumns(10);
	}
	
	private void botones() {
		btnAgregar = new JButton("Aceptar");
		btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				String genero = (String)cbCategoria.getSelectedItem();
				String titulo = txtNombre.getText();
				
				if(genero == "Seleccione un genero" || titulo.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Todos los campos deben completarse");
					return;
				}
			
				//Agregar película a la lista
				listModel.addElement(new Pelicula(titulo, new Categoria(genero)));
				
				//Actualizar GUI
				actualizarNumeroID();
				
				//Mostrar mensaje de éxito
				JOptionPane.showMessageDialog(null, "La pelicula " + titulo + " se agrego exitosamente");
			}
		});
		btnAgregar.setBounds(46, 139, 135, 23);
		add(btnAgregar);
	}
	
	public void actualizarNumeroID() {
		lblNumeroid.setText(String.valueOf(Pelicula.getIdPelicula()));
	}

	public void setDefaultListModelt(DefaultListModel<Pelicula> listModel) {
		this.listModel = listModel;
	}
}
