package tp5;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListarPeliculas extends JPanel implements IPanelList{
	
	private static final long serialVersionUID = 1L;
	private JList<Peliculas> jlPeliculas;
	private JLabel lblPeliculas; 
	private static DefaultListModel<Peliculas> listModel;
	
	public ListarPeliculas() {
		configuracion();
	}
	
	private void configuracion() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
	 jlPeliculas = new JList();
	 jlPeliculas.setBounds(10, 32, 410, 298);
	 add(jlPeliculas);
	 
	lblPeliculas = new JLabel("Listado:");
	lblPeliculas.setBounds(10, 7, 103, 14);
	add(lblPeliculas);
	}

	//Metodo de Interface
	public void setDefaultListModelt(DefaultListModel<Peliculas> listModel) {
			this.listModel = listModel;
			jlPeliculas.setModel(this.listModel);
			ordenarPeliculasAlfabeticamente();
	}
	
	//Metodo para ordenar peliculas
	private void ordenarPeliculasAlfabeticamente() {
		List<Peliculas> peliculas = Collections.list(listModel.elements());
        Collections.sort(peliculas, (peli1, peli2) -> 
        peli1.getNombre().compareTo(peli2.getNombre()));
        listModel.clear();      
        for (Peliculas pelicula : peliculas) {
            listModel.addElement(pelicula);
        }
	}
}
