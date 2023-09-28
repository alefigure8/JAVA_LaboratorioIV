package ejercicio;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Font;
import java.util.Arrays;

public class ListarPeliculas extends JPanel implements Panel{
	private static final long serialVersionUID = 1L;
	private JList<Pelicula> list;
	private DefaultListModel<Pelicula> listModel;
	private JLabel lblPeliculas;

	public ListarPeliculas() {
		configuracion();
		labels();
		lista();
		
	}

	private void configuracion() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
	}

	private void labels() {
		lblPeliculas = new JLabel("Peliculas");
		lblPeliculas.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPeliculas.setBounds(32, 104, 70, 14);
		add(lblPeliculas);
	}

	private void lista() {
		list = new JList<Pelicula>();
		list.setBounds(110, 11, 306, 199);
		add(list);
	}
	
	private void ordenarLista() {
		ListModel<Pelicula> model = list.getModel();
		int cant = model.getSize();
		
		Pelicula data[] = new Pelicula[cant];
		
		for(int i = 0; i < cant; i++) {
			data[i] = (Pelicula)model.getElementAt(i);
		}
		
		Arrays.sort(data, (a,b) -> a.getNombre().compareTo(b.getNombre()));	
		
		list.setListData(data);
	}

	public void setDefaultListModelt(DefaultListModel<Pelicula> listModel) {
		this.listModel = listModel;
		list.setModel(this.listModel);
		
		//Ordenar lista
		ordenarLista();
	}
}
