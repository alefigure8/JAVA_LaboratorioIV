package desktop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class frame extends JFrame {

	private JPanel contentPane;
	private JPanel comboBoxItems;
	private JButton btnAceptar;
	private JComboBox<ItemsCB> comboBox;
	private JTextField txtKey;
	private JTextField txtValue;
	private JList list;
	private DefaultListModel<ItemsCB> listModel;
	private JMenuBar menuBar;

	//CONSTRUCTOR
	public frame() {
		
		//SET VENTANA
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Titulo");
		PanelComboBox();
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
				
		//MENU
		JMenu mnNewMenu = new JMenu("Menu 1");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("comboBox");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				PanelComboBox();
				contentPane.repaint();
				contentPane.validate();
			}
		});
		
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmLista = new JMenuItem("Lista");
		mntmLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				PanelList();
				contentPane.repaint();
				contentPane.validate();
			}
		});
		
		mnNewMenu.add(mntmLista);
		
				
	}
	
	private void Combobox() {
		comboBox = new JComboBox<ItemsCB>();
		comboBox.setBounds(44, 59, 136, 20);
		comboBox.addItem(new ItemsCB(1, "Uno"));
		comboBox.addItem(new ItemsCB(2, "Dos"));
		comboBox.addItem(new ItemsCB(3, "Tres"));
		comboBox.addItem(new ItemsCB(4, "Cuatro"));
		comboBoxItems.add(comboBox);
	}
	
	private void Botones() {
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ItemsCB aux = (ItemsCB)comboBox.getSelectedItem();
				JOptionPane.showMessageDialog(null, "Localidad seleccionda " + aux.value);
			}
		});
		btnAceptar.setBounds(190, 58, 89, 23);
		comboBoxItems.add(btnAceptar);
				
			}
	
	public void PanelComboBox() {
		comboBoxItems = new JPanel();
		comboBoxItems.setBorder(new EmptyBorder(5, 5, 5, 5));
		comboBoxItems.setLayout(null);
		setContentPane(comboBoxItems);
		
		Combobox();
		Botones();	
	}
	
	public void PanelList() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		txtKey = new JTextField();
		txtKey.setBounds(44, 209, 86, 20);
		contentPane.add(txtKey);
		txtKey.setColumns(10);
		
		txtValue = new JTextField();
		txtValue.setColumns(10);
		txtValue.setBounds(147, 209, 86, 20);
		contentPane.add(txtValue);
		
		JButton btnAddOption = new JButton("Aceptar");
		btnAddOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String value = txtValue.getText();
				int key = Integer.parseInt(txtKey.getText());
				ItemsCB aux = new ItemsCB(key, value);
				comboBox.addItem(aux);
				listModel.addElement(aux);
				
			}
		});
		btnAddOption.setBounds(237, 208, 89, 23);
		contentPane.add(btnAddOption);
		
		list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(list.getSelectedIndex() != -1) {
					listModel.remove(list.getSelectedIndex());
				}
			}
		});
		
		list.setBounds(44, 103, 282, 78);
		listModel = new DefaultListModel<ItemsCB>();
		list.setModel(listModel);
		contentPane.add(list);
	}
	
	public class ItemsCB{
		private int key;
		private String value;
		
		public ItemsCB(int key, String value) {
			
			this.key = key;
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
		
		public int getKey() {
			return this.key;
		}
		
		@Override
		public String toString() {
			return this.key + " - " + this.value;
		}
	}
}
