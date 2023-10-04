package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class panel extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public panel() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 260);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);

	}

}
