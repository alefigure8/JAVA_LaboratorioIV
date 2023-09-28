package desktop;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public class PanelListadoLibros extends JPanel{
	private JList<Libro> list;
	private DefaultListModel<Libro> listModel;
	
	public PanelListadoLibros() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		list = new JList<Libro>();
		list.setBounds(82, 68, 298, 172);
		add(list);
	}
	
	public void setListModel(DefaultListModel<Libro> listModel2) {
		this.listModel = listModel2;
		list.setModel(this.listModel);
	}
	
}
