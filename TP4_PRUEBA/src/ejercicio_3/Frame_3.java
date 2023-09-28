package ejercicio_3;


import java.awt.CheckboxGroup;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frame_3 extends JFrame{
	private JPanel contentPane;
	private JTextField txtHoras;
	private ButtonGroup checkboxGrupo;
	
	public Frame_3() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		setTitle("Seleccion multiple");
		contentPane.setLayout(null);
		
		//PANEL SO
		JPanel elegirSO = new JPanel();
		elegirSO.setBounds(32, 26, 370, 40);
		contentPane.add(elegirSO);
		elegirSO.setBorder(BorderFactory.createLineBorder(Color.black));
		elegirSO.setLayout(null);
		
		//LABEL
		JLabel lblElijeUnSistema = new JLabel("Elije un sistema operativo");
		lblElijeUnSistema.setBounds(10, 11, 143, 14);
		elegirSO.add(lblElijeUnSistema);
		
		//RADIO BUTTON WINDOWS
		JRadioButton rdbtnWindows = new JRadioButton("Windows");
		rdbtnWindows.setBounds(149, 7, 87, 23);
		rdbtnWindows.setActionCommand(rdbtnWindows.getText());
		elegirSO.add(rdbtnWindows);
		
		//RADIO BUTTON MAC
		JRadioButton rdbtnMac = new JRadioButton("Mac");
		rdbtnMac.setBounds(238, 7, 59, 23);
		rdbtnMac.setActionCommand(rdbtnMac.getText());
		elegirSO.add(rdbtnMac);
		
		//RADIO BUTTON LINUX
		JRadioButton rdbtnLinux = new JRadioButton("Linux");
		rdbtnLinux.setBounds(299, 7, 65, 23);
		rdbtnLinux.setActionCommand(rdbtnLinux.getText());
		elegirSO.add(rdbtnLinux);
		
		//GRUPO CHECKBOX
		checkboxGrupo = new ButtonGroup();
		checkboxGrupo.add(rdbtnWindows);
		checkboxGrupo.add(rdbtnMac);
		checkboxGrupo.add(rdbtnLinux);
		
		//PANEL ESPECIALIDAD
		JPanel especialidad = new JPanel();
		especialidad.setBounds(32, 77, 370, 97);
		contentPane.add(especialidad);
		especialidad.setBorder(BorderFactory.createLineBorder(Color.black));
		especialidad.setLayout(null);
		
		//LABEL ESPECIALIDAD
		JLabel lblElijeUnaEspecialidad = new JLabel("Elije una especialidad");
		lblElijeUnaEspecialidad.setBounds(10, 42, 123, 14);
		especialidad.add(lblElijeUnaEspecialidad);
		
		//CHECKBOX PROGRAMACION
		JCheckBox chckbxProgramacin = new JCheckBox("Programaci\u00F3n");
		chckbxProgramacin.setBounds(207, 7, 97, 23);
		especialidad.add(chckbxProgramacin);
		
		//CHECKBOX ADMINISTRACION
		JCheckBox chckbxAdministracin = new JCheckBox("Administraci\u00F3n");
		chckbxAdministracin.setBounds(207, 38, 97, 23);
		especialidad.add(chckbxAdministracin);
		
		//CHECKBOX DISEÑO GRAFICO
		JCheckBox chckbxDiseoGrfico = new JCheckBox("Dise\u00F1o Gr\u00E1fico");
		chckbxDiseoGrfico.setBounds(207, 67, 97, 23);
		especialidad.add(chckbxDiseoGrfico);
				
		//LABEL CANTIDAD HORAS
		JLabel lblCantidadDeHoras = new JLabel("Cantidad de horas en el  computador: ");
		lblCantidadDeHoras.setBounds(32, 185, 227, 14);
		contentPane.add(lblCantidadDeHoras);
		
		//TEXTFIELD HORAS
		txtHoras = new JTextField();
		txtHoras.setBounds(269, 182, 109, 20);
		contentPane.add(txtHoras);
		txtHoras.setColumns(10);
		
		//BOTON ACEPTAR
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//SO
				String SO = obtenerResultadoRadioButton();
				
				String resultado = SO;
				
				//ESPECIALIDAD
				boolean programacion = chckbxProgramacin.isSelected();
				boolean administracion = chckbxAdministracin.isSelected();
				boolean diseno = chckbxDiseoGrfico.isSelected();
							
				if(programacion) {
					resultado = resultado + " - " + chckbxProgramacin.getText();
				}
				
				if(administracion) {
					resultado = resultado + " - " + chckbxAdministracin.getText();
				}
				
				if(diseno) {
					resultado = resultado + " - " + chckbxDiseoGrfico.getText();
				}
				
				//HORAS
				resultado = resultado + " - " + txtHoras.getText() + " Hs";
				
				//MESSAGE BOX
				JOptionPane.showOptionDialog(null, 
				        resultado, 
				        "Mensaje", 
				        JOptionPane.OK_OPTION, 
				        JOptionPane.INFORMATION_MESSAGE, 
				        null, 
				        new String[]{"Aceptar"},
				        "default");
			}
		});
		btnAceptar.setBounds(308, 213, 89, 23);
		contentPane.add(btnAceptar);
	}
	
	public void visibilidadFrame(Boolean opcion) {
		this.setVisible(opcion);
	}
	
	private String obtenerResultadoRadioButton() {
		String value = checkboxGrupo.getSelection().getActionCommand();
		return value;
	}
}
