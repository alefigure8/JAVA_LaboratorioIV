package ejercicio_1;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frame_1 extends JFrame{
	
	private JPanel contentPane;
	private int labelPosicionY = 50;
	private int labelPositionX = 50;
	private int distanciaLabelY = 35;
	
	private int textFieldPositionY = 50;
	private int textFieldPositionX = 130;
	private int textFieldSizeWidth=200;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtFechaNac;
	
	private JLabel lblResultado;
	
	public Frame_1() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//LABELS
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(labelPositionX, labelPosicionY, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		//lblApellido.setBounds(labelPositionX, labelPosicionY+=distanciaLabelY, 46, 14);
		lblApellido.setBounds(labelPositionX, labelPosicionY+distanciaLabelY, 46, 14);
		contentPane.add(lblApellido);
		
		JLabel lblTelefono = new JLabel("Telefono");
		//lblTelefono.setBounds(labelPositionX, labelPosicionY+=distanciaLabelY, 46, 14);
		lblTelefono.setBounds(labelPositionX, labelPosicionY+distanciaLabelY+distanciaLabelY, 46, 14);
		contentPane.add(lblTelefono);
		
		JLabel lblFechaNac = new JLabel("Fecha Nac.");
		//lblFechaNac.setBounds(labelPositionX, labelPosicionY+=distanciaLabelY, 70, 14);
		lblFechaNac.setBounds(labelPositionX, labelPosicionY+distanciaLabelY+distanciaLabelY+distanciaLabelY, 70, 14);
		contentPane.add(lblFechaNac);
		
		//TEXTFIELDS
		txtNombre = new JTextField();
		txtNombre.setBounds(textFieldPositionX, 47, textFieldSizeWidth, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(textFieldPositionX, 82, textFieldSizeWidth, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(textFieldPositionX, 117, textFieldSizeWidth, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtFechaNac = new JTextField();
		txtFechaNac.setBounds(textFieldPositionX, 152, textFieldSizeWidth, 20);
		contentPane.add(txtFechaNac);
		txtFechaNac.setColumns(10);
		
		//BOTON
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			validarFormulario();
			}
		});
		btnMostrar.setBounds(241, 183, 89, 23);
		contentPane.add(btnMostrar);
		
		//LABEL
		JLabel lblMostrarDatos = new JLabel("Los datos ingresados fueron:");
		lblMostrarDatos.setBounds(50, 209, 341, 14);
		contentPane.add(lblMostrarDatos);
		
		//LABEL RESULTADO
		lblResultado = new JLabel("Resultado");
		lblResultado.setBounds(50, 234, 341, 14);
		contentPane.add(lblResultado);
		lblResultado.setVisible(false);
				
		//TITULO
		setTitle("Contactos");
	}
	
	//VISIBILIDAD DE LA VENTANA
	public void visibilidadFrame(Boolean opcion) {
		this.setVisible(opcion);
	}
	
	//VALIDR EL CUESTIONARIO
	public void validarFormulario() {
		
		//DATOS DEL FORMULARIO
		String nombre = txtNombre.getText();
		String apellido = txtApellido.getText();
		String telefono = txtTelefono.getText();
		String fechaNac = txtFechaNac.getText();
		
		//SI TODOS LOS DATOS SE ENCUENTRAN
		if(	!nombre.isEmpty() && 
			!apellido.isEmpty() &&
			!telefono.isEmpty() &&
			!fechaNac.isEmpty()) 
		{
			//VALIDAR NUMEROS DE TELEFONO Y FECHA DE NACIMIENTO
			if(verificarNumeros(telefono) && verificarFecha(fechaNac)){

				//MOSTRAR RESULTADO
				lblResultado.setVisible(true);
				lblResultado.setText(nombre + " " + apellido + " " + telefono + " " + fechaNac);
				
				//LIMPIAR CUESTIONARIO
				limpiarCuestionario();
			}

			return;
		}
			
		//SI FALTA EL NOMBRE
		if(nombre.isEmpty()) {
			txtNombre.setBackground(Color.RED);
		} else {
			txtNombre.setBackground(Color.WHITE);
		}
		
		//SI FALTA EL APELLIDO
		if(apellido.isEmpty()) {
			txtApellido.setBackground(Color.RED);
		}else {
			txtApellido.setBackground(Color.WHITE);
		}
		
		//SI FALTA EL TOLEFONO
		if(telefono.isEmpty()) {
			txtTelefono.setBackground(Color.RED);
		}else {
			txtTelefono.setBackground(Color.WHITE);
		}
		
		//SI FALTA LA FECHA DE NACIMIENTO
		if(fechaNac.isEmpty()) {
			txtFechaNac.setBackground(Color.RED);
		}else {
			txtFechaNac.setBackground(Color.WHITE);
		}
	}
	
	//LIMPIAR CUESTIONARIO
	public void limpiarCuestionario() {
		txtNombre.setText(null);
		txtApellido.setText(null);
		txtTelefono.setText(null);
		txtFechaNac.setText(null);
		txtNombre.setBackground(Color.WHITE);
		txtApellido.setBackground(Color.WHITE);
		txtTelefono.setBackground(Color.WHITE);
		txtFechaNac.setBackground(Color.WHITE);
	}
	
	//VERIFICAR QUE SEAN NUMEROS
	public boolean verificarNumeros(String numeros) {
		boolean soloNumeros = (numeros != null && numeros.matches("^[0-9]*$"));
		return soloNumeros;
	}

	//VERIFICAR QUE SEA UNA FECHA VALIDA
	public boolean verificarFecha(String fecha) {
		boolean fechaValida = (fecha != null && fecha.matches("^([0-9]{2})/([0-9]{2})/([0-9]{4})$"));
		return fechaValida;
	}
}
