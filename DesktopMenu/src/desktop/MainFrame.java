package desktop;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

//PARA CREAR UN MENU
//Primero incorporar un JMenuBar
//Luego adentro del JMenuBar. agregar un JMenu
//Adentro incorporar JMenuItems


public class MainFrame extends JFrame {

	private JPanel contentPane;
	private static DefaultListModel<Libro> listModel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					//INSTANCIO EL LISTMODEL
					listModel = new DefaultListModel<Libro>();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {

		setTitle("Formulario libros");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Libros");
		menu.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(menu);
		
		JMenuItem menuItemAgregarLibros = new JMenuItem("AgregarLibros");
		menuItemAgregarLibros.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		menuItemAgregarLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				contentPane.removeAll();
				//Abre el formulario PanelIngresoLibros dentro de la ventana principal
				PanelAgregarLibros panel = new PanelAgregarLibros();
				panel.setListModel(listModel);
				contentPane.add(panel);
				contentPane.repaint();
				contentPane.revalidate();
				
			}
		}); 
		menu.add(menuItemAgregarLibros);
		
		JMenuItem menuItemListadoLibros = new JMenuItem("Listado Libros");
		menuItemListadoLibros.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		menuItemListadoLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Abre el formulario PanelListadoLibros
				contentPane.removeAll();
				PanelListadoLibros panel = new PanelListadoLibros();
				panel.setListModel(listModel);
				contentPane.add(panel);
				contentPane.repaint();
				contentPane.revalidate();
				
			}
		});
		menu.add(menuItemListadoLibros);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
