package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;

public class VentanaListar extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scrollPane;
		
	public VentanaListar() {
		
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 24, 429, 243);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.add(table);
		table.setFillsViewportHeight(true); 
		scrollPane.setViewportView(table);	

		

	}
	
	public void setDefaultTableModel(DefaultTableModel tableModel) {

		this.tableModel = tableModel;
		table.setModel(tableModel);
	}
}
