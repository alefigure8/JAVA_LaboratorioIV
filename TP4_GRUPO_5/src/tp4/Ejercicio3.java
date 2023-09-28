package tp4;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;

public class Ejercicio3 extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel radioButtonsSO;
	private JLabel lblElijeUnSistema;
	private JRadioButton rdbtnWindows;
	private JRadioButton rdbtnMac;
	private JRadioButton rdbtnLinux;
	private ButtonGroup grupoRBSistemasOperativos;
	
	public Ejercicio3() {
		
		setTitle("Sistemas Operativos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(250, 420, 450, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		radioButtonsSO = new JPanel();
		radioButtonsSO.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		radioButtonsSO.setBounds(10, 11, 414, 48);
		getContentPane().add(radioButtonsSO);
		radioButtonsSO.setLayout(null);
		
		lblElijeUnSistema = new JLabel("Elije un sistema operativo: ");
		lblElijeUnSistema.setBounds(10, 15, 158, 14);
		radioButtonsSO.add(lblElijeUnSistema);
		
		rdbtnWindows = new JRadioButton("Windows");
		rdbtnWindows.setBounds(179, 11, 90, 23);
		rdbtnWindows.setActionCommand(rdbtnWindows.getText());
		radioButtonsSO.add(rdbtnWindows);
		
		rdbtnMac = new JRadioButton("Mac");
		rdbtnMac.setBounds(271, 11, 60, 23);
		rdbtnMac.setActionCommand(rdbtnMac.getText());
		radioButtonsSO.add(rdbtnMac);
		
		rdbtnLinux = new JRadioButton("Linux");
		rdbtnLinux.setBounds(333, 11, 75, 23);
		rdbtnLinux.setActionCommand(rdbtnLinux.getText());
		radioButtonsSO.add(rdbtnLinux);
		
		grupoRBSistemasOperativos = new ButtonGroup();
		grupoRBSistemasOperativos.add(rdbtnMac);
		grupoRBSistemasOperativos.add(rdbtnWindows);
		grupoRBSistemasOperativos.add(rdbtnLinux);
		
		setVisible(true);
	}
	
	//OBTENER OPCION ELEGIDA
	private String obtenerResultadoRadioButton() {
		String value = grupoRBSistemasOperativos.getSelection().getActionCommand();
		return value;
	}
}