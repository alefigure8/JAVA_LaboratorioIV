package desktop;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelAgregarLibros extends JPanel{
	private JTextField txtTitulo;
	private JTextField txtCapitulos;
	private DefaultListModel<Libro> listModel;
	private JButton btnAceptar;
	
	public PanelAgregarLibros() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(56, 72, 219, 20);
		add(txtTitulo);
		txtTitulo.setColumns(10);
		
		txtCapitulos = new JTextField();
		txtCapitulos.setBounds(56, 111, 219, 20);
		add(txtCapitulos);
		txtCapitulos.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.addElement(new Libro(Integer.parseInt(txtCapitulos.getText()),txtTitulo.getText()));
			}
		});
		btnAceptar.setBounds(53, 153, 89, 23);
		add(btnAceptar);
	}
	
	public void setListModel(DefaultListModel<Libro> listModel) {
		this.listModel = listModel;
	}

}
