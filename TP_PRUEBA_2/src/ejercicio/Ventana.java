package ejercicio;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnPeliculas;
	private JMenuItem mntmAgregar;
	private JMenuItem mntmListar;
	private static DefaultListModel<Pelicula> listModel;

	public Ventana() {
		configuracion();
		listModel();
		panel();
		menu();
	}

	public void configuracion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Programa");
	}

	public void listModel() {
		listModel = new DefaultListModel<Pelicula>();
	}

	public void panel() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	public void menu() {
		// Barra Menu
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// Boton Contenedor Peliculas
		mnPeliculas = new JMenu("Peliculas");
		menuBar.add(mnPeliculas);

		// Boton Agregar
		mntmAgregar = new JMenuItem("Agregar");
		mntmAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iniciarPanel(new AgregarPelicula());
			}
		});
		mntmAgregar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnPeliculas.add(mntmAgregar);

		// Boton listar
		mntmListar = new JMenuItem("Listar");
		mntmListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				iniciarPanel(new ListarPeliculas());
			}
		});
		mntmListar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnPeliculas.add(mntmListar);
	}
	
	private void iniciarPanel(Panel panel){
		contentPane.removeAll();
		panel.setDefaultListModelt(listModel);
		contentPane.add((JPanel)panel);
		contentPane.repaint();
		contentPane.revalidate();
	}
}