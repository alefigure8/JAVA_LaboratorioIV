package ejercicio_2;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;

import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Frame_2 extends JFrame{
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
	private JComboBox<Items> cb_TPS;
	
	public Frame_2() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//PANEL 1
		notasEstudiante = new JPanel();
		notasEstudiante.setBounds(32, 11, 258, 128);
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
		cb_TPS = new JComboBox<Items>();
		cb_TPS.setBounds(83, 99, 151, 20);
		cb_TPS.addItem(new Items("1", "APROBADO"));
		cb_TPS.addItem(new Items("2", "DESAPROBADO"));
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
		txtNota_3.setBounds(84, 74, 150, 17);
		notasEstudiante.add(txtNota_3);
				
		//PANEL 2
		notasEstudianteResultado = new JPanel();
		notasEstudianteResultado.setBounds(32, 148, 258, 97);
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
		
		//TXT PROMEDIO
		txtPromedio = new JTextField();
		txtPromedio.setColumns(10);
		txtPromedio.setBounds(84, 25, 150, 17);
		notasEstudianteResultado.add(txtPromedio);
		
		//TXT CONDICION
		txtCondicion = new JTextField();
		txtCondicion.setColumns(10);
		txtCondicion.setBounds(84, 50, 150, 17);
		notasEstudianteResultado.add(txtCondicion);
		
		//BOTON CALCULAR
		btnCalcular = new JButton("CALCULAR");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				validarFormulario();
				
			}
		});
		btnCalcular.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCalcular.setBounds(300, 53, 110, 40);
		contentPane.add(btnCalcular);
		
		//BOTON NUEVO
		btnNuevo = new JButton("NUEVO");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
			}
		});
		btnNuevo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNuevo.setBounds(300, 99, 110, 40);
		contentPane.add(btnNuevo);
		
		//BOTON SALIR
		btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				visibilidadFrame(false);
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSalir.setBounds(300, 144, 110, 40);
		contentPane.add(btnSalir);
		
		setTitle("Promedio");
	}
	
	public void visibilidadFrame(Boolean opcion) {
		this.setVisible(opcion);
	}
	
	public void limpiarFormulario()
	{
		txtNota_1.setText(null);
		txtNota_2.setText(null);
		txtNota_3.setText(null);
		txtCondicion.setText(null);
		txtPromedio.setText(null);
	}
	
	public boolean verificarNumeros(String numeros) {
		boolean soloNumeros = (numeros != null && numeros.matches("^([+-]?\\d*\\.?\\d*)$"));
		return soloNumeros;
	}
	
	//VERIFICA CONDICION DE TPS
	public boolean condicionTPS(JComboBox<Items> comboBox) {
		String valor = comboBox.getSelectedItem().toString();
		
		if(valor == "APROBADO")
		{
			return true;
		}
		
		return false;
	}
	
	public void validarFormulario() {
		//VALIDAR QUE SEAN NUMEROS
		if(	verificarNumeros(txtNota_1.getText()) && 
			verificarNumeros(txtNota_2.getText()) && 
			verificarNumeros(txtNota_3.getText()))
		{
			
			//PARSE
			Double nota1 = Double.parseDouble(txtNota_1.getText());
			Double nota2 = Double.parseDouble(txtNota_2.getText());
			Double nota3 = Double.parseDouble(txtNota_3.getText());
			
			//SI LAS NOTAS SON MAYOR A 10
			if(	nota1 <= 10 && 
				nota2 <= 10 && 
				nota3 <= 10) 
			{
				//PROMEDIO
				double[] notas = new double[] {nota1, nota2, nota3};
				txtPromedio.setText(new Double(Arrays.stream(notas).average().orElse(Double.NaN)).toString());
				
				//SI NO TIENE LOS TPS APROBADO = LIBRE
				if(!condicionTPS(cb_TPS)) {
					txtCondicion.setText("LIBRE");
					return;
				}
				
				//SI ALGUNA NOTA ES MENORES A 6 = LIBRE
				if(	nota1 < 6 ||  
					nota2 < 6 || 
					nota3 < 6) 
				{
					txtCondicion.setText("LIBRE");
					return;
				}
				
				//SI LAS NOTAS SON SUPERIOS A 8 Y TP APROBADO = PROMOCIONADO
				if(	nota1 >= 8 &&  
					nota2 >= 8 && 
					nota3 >= 8 &&
					condicionTPS(cb_TPS)) 
				{
					txtCondicion.setText("PROMOCIONADO");
					return;
				}
				
				//SI LAS NOTAS ESTAN ENTRE 6 y 8 Y TP APROBADO = REGULAR
				if(	nota1 >= 6 && nota1 < 8 ||
					nota2 >= 6 && nota2 < 8 ||
					nota3 >= 6 && nota3 < 8 ||
					condicionTPS(cb_TPS))
				{
					txtCondicion.setText("REGULAR");
					return;
				}
				
			}

		}
	}
	
	//COMBOBOX ITEMS CLASS
	public class Items{
		 private String value;
	    private String text;
	    
	    public Items(String value, String text) {
	        this.value = value;
	        this.text = text;
	    }
	    
	    @Override
	    public String toString() {
	        return text;
	    }
	}
}

