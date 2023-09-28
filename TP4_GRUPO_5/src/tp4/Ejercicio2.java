package tp4;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

public class Ejercicio2 extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel notasEstudiante;
	private JPanel notasEstudianteResultado;
	private JTextField txtNota_1;
	private JTextField txtNota_2;
	private JTextField txtNota_3;
	private JTextField txtPromedio;
	private JTextField txtCondicion;
	private JButton btnCalcular;
	private JButton btnNuevo;
	private JButton btnSalir;
	private JComboBox<String> cb_TPS;
	
	public Ejercicio2() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700, 420, 547, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//PANEL 1
		notasEstudiante = new JPanel();
		notasEstudiante.setBounds(10, 9, 258, 128);
		contentPane.add(notasEstudiante);
		notasEstudiante.setBorder(BorderFactory.createTitledBorder("Notas del estudiante"));
		notasEstudiante.setLayout(null);
		
		//LABEL NOTA 1
		JLabel lblNota_1 = new JLabel("Nota 1");
		lblNota_1.setBounds(21, 27, 46, 14);
		notasEstudiante.add(lblNota_1);
		
		//LABEL NOTA 2
		JLabel lblNota_2 = new JLabel("Nota 2");
		lblNota_2.setBounds(21, 52, 46, 14);
		notasEstudiante.add(lblNota_2);
				
		//LABEL NOTA 3
		JLabel lblNota_3 = new JLabel("Nota 3");
		lblNota_3.setBounds(21, 77, 46, 14);
		notasEstudiante.add(lblNota_3);
		
		//COMBOBOX
		cb_TPS = new JComboBox<String>();
		cb_TPS.setBounds(83, 99, 150, 20);
		cb_TPS.addItem("APROBADO");
		cb_TPS.addItem("DESAPROBADO");
		cb_TPS.setSelectedIndex(-1);
		notasEstudiante.add(cb_TPS);
				
		//LABEL TPS
		JLabel lblTps = new JLabel("TPS");
		lblTps.setBounds(21, 102, 46, 14);
		notasEstudiante.add(lblTps);
		
		//TXT NOTA
		txtNota_1 = new JTextField();
		txtNota_1.setBounds(83, 25, 150, 17);
		notasEstudiante.add(txtNota_1);
		txtNota_1.setColumns(10);
		
		//TXT NOTA 2
		txtNota_2 = new JTextField();
		txtNota_2.setColumns(10);
		txtNota_2.setBounds(83, 49, 150, 17);
		notasEstudiante.add(txtNota_2);
		
		//TXT NOTA 3
		txtNota_3 = new JTextField();
		txtNota_3.setColumns(10);
		txtNota_3.setBounds(83, 74, 150, 17);
		notasEstudiante.add(txtNota_3);
				
		//PANEL 2
		notasEstudianteResultado = new JPanel();
		notasEstudianteResultado.setBounds(10, 148, 258, 97);
		contentPane.add(notasEstudianteResultado);
		notasEstudianteResultado.setBorder(BorderFactory.createTitledBorder("Notas del estudiante"));
		notasEstudianteResultado.setLayout(null);
		
		//LABEL PROMEDIO
		JLabel lblPromedio = new JLabel("Promedio");
		lblPromedio.setBounds(20, 28, 64, 14);
		notasEstudianteResultado.add(lblPromedio);
		
		//LABEL CONDICION
		JLabel lblCondicion = new JLabel("Condicion");
		lblCondicion.setBounds(20, 53, 64, 14);
		notasEstudianteResultado.add(lblCondicion);
		
		//LABEL ERROR
		JLabel lblError = new JLabel("");
		lblError.setBounds(292, 34, 229, 23);
		lblError.setForeground(Color.RED);
		contentPane.add(lblError);
		
		
		//TXT PROMEDIO
		txtPromedio = new JTextField();
		txtPromedio.setColumns(10);
		txtPromedio.setBounds(83, 26, 150, 17);
		txtPromedio.setEditable(false);
		notasEstudianteResultado.add(txtPromedio);
		
		//TXT CONDICION
		txtCondicion = new JTextField();
		txtCondicion.setColumns(10);
		txtCondicion.setBounds(83, 51, 150, 17);
		txtCondicion.setEditable(false);
		notasEstudianteResultado.add(txtCondicion);
		
		//BOTON CALCULAR
		btnCalcular = new JButton("CALCULAR");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!chequeoNotasVacias()) {
					lblError.setText("No se permiten campos vacios");
					return;
				}
                else {
					
					lblError.setText("");
					
				}
				
				if(!chequeoNotas()) {
					
					lblError.setText("Las notas deben ser entre 1 y 10");
					return;
				}
				else {
					
					lblError.setText("");
					
				}
				if(cb_TPS.getSelectedIndex() == -1) {
					lblError.setText("Debe seleccionar un estado de TPS");
					return;
				}
				else {
					lblError.setText("");
				}
				
				
				calcularResultado();
			}
		});
		btnCalcular.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCalcular.setBounds(340, 89, 110, 40);
		contentPane.add(btnCalcular);
		
		//BOTON NUEVO
		btnNuevo = new JButton("NUEVO");
		btnNuevo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNuevo.setBounds(340, 142, 110, 40);
		contentPane.add(btnNuevo);
		
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				restaurarCampos();
				lblError.setText("");
			}
		});
		
		
		
		
		
		//BOTON SALIR
		btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose(); // cierra el frame activo
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSalir.setBounds(340, 193, 110, 40);
		contentPane.add(btnSalir);
		
		
		
		setTitle("Promedio");
		setVisible(true);
	}
	
	//Calculamos promedio y condicion
	public void calcularResultado() {
		
			double nota1=Double.parseDouble(txtNota_1.getText());
			double nota2=Double.parseDouble(txtNota_2.getText());
			double nota3=Double.parseDouble(txtNota_3.getText());
			String tps= (String)cb_TPS.getSelectedItem();
			String condicion;
			double promedio;
			
			if(tps=="DESAPROBADO" || nota1<6 || nota2<6 || nota3<6) {
				condicion="Libre";
			}
			else if(nota1>=8 && nota2>=8 && nota3>=8) {
				condicion="Promocionado";
			}
			else {
				condicion="Regular";
			}


			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			promedio=(nota1+nota2+nota3)/3;
			txtPromedio.setText(String.valueOf(decimalFormat.format(promedio)));
			
			//promedio=(nota1+nota2+nota3)/3;
			txtCondicion.setText(condicion);
			//txtPromedio.setText(String.valueOf(promedio));
			
		
	}
	
	public void restaurarCampos() {
		
		txtNota_1.setText("");
		txtNota_2.setText("");
		txtNota_3.setText("");
        cb_TPS.setSelectedIndex(-1);
		txtPromedio.setText(""); 	
		txtCondicion.setText("");
		
		
	}
	//chequeo que las notas no sean menor a 1 o mayor a 10
	public Boolean chequeoNotas() {
		double nota1=Double.parseDouble(txtNota_1.getText());
		double nota2=Double.parseDouble(txtNota_2.getText());
		double nota3=Double.parseDouble(txtNota_3.getText());
		
		if(nota1 < 1 || nota1 >10 || nota2 < 1 || nota2 >10 || nota3<1 || nota3>10 ) {
			restaurarCampos();
			return false;
		}
		
		return true;
	}
	
	public Boolean chequeoNotasVacias() {
		
		if(txtNota_1.getText().isEmpty() || txtNota_2.getText().isEmpty() || txtNota_3.getText().isEmpty()) {
			restaurarCampos();
		
			return false;
		}
		
		return true;
	}
}
