package tp4;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Ejercicio1 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtFechaNac;
	
	
	public Ejercicio1() {
		setTitle("Contactos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700, 100, 432, 321);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(59, 65, 56, 16);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(59, 94, 56, 16);
		contentPane.add(lblApellido);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(59, 123, 56, 16);
		contentPane.add(lblTelefono);
		
		JLabel lblFechaNac = new JLabel("Fecha Nac");
		lblFechaNac.setBounds(59, 152, 86, 16);
		contentPane.add(lblFechaNac);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(162, 62, 134, 22);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(162, 91, 134, 22);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(162, 120, 134, 22);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtFechaNac = new JTextField();
		txtFechaNac.setBounds(162, 149, 134, 22);
		contentPane.add(txtFechaNac);
		txtFechaNac.setColumns(10);
		
		JButton btnMostrar = new JButton("Mostrar");
		
		btnMostrar.setBounds(199, 182, 97, 25);
		contentPane.add(btnMostrar);
		
		JLabel lblLosDatosIngresados = new JLabel("Los datos ingresados fueron:");
		lblLosDatosIngresados.setVerticalAlignment(SwingConstants.TOP);
		lblLosDatosIngresados.setBounds(30, 213, 358, 37);
		contentPane.add(lblLosDatosIngresados);
		
		JLabel lblResultado1 = new JLabel("Resultado1");
		lblResultado1.setHorizontalAlignment(SwingConstants.LEFT);
		lblResultado1.setBounds(151, 234, 254, 16);
		lblResultado1.setVisible(false);
		contentPane.add(lblResultado1);
		lblResultado1.setHorizontalAlignment(SwingConstants.LEFT);
		

		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean camposVacios = false; // Variable para rastrear si hay campos vacï¿½os
		        
		        if (txtApellido.getText().trim().isEmpty()) {
		            txtApellido.setBackground(Color.RED);
		            camposVacios = true; // Marcar que al menos un campo estï¿½ vacï¿½o
		        } else {
		            txtApellido.setBackground(Color.WHITE);
		            
		        }
		        
		        if (txtNombre.getText().trim().isEmpty()) {
		            txtNombre.setBackground(Color.RED);
		            camposVacios = true;
		        } else {
		            txtNombre.setBackground(Color.WHITE);
		             
		        }
		        
		        if (txtFechaNac.getText().trim().isEmpty()) {
		            txtFechaNac.setBackground(Color.RED);
		            camposVacios = true;
		        } else {
		            txtFechaNac.setBackground(Color.WHITE);
		             
		        }
		        
		        if (txtTelefono.getText().trim().isEmpty()) {
		            txtTelefono.setBackground(Color.RED);
		            camposVacios = true;
		        } else {
		            txtTelefono.setBackground(Color.WHITE);
		             
		        }
		        
		        System.out.println("camposVacios: " + camposVacios);
			        
		        if (camposVacios) {
			        	//txtApellido.setText("");
			            //txtNombre.setText("");
			            //txtFechaNac.setText("");
			            //txtTelefono.setText("");
			        	lblLosDatosIngresados.setText("No puede haber campos vacios");
			 			        }		      		        
			        
			        else {
			        	lblResultado1.setVisible(false);
			        	
			        	/*if(!verificarFecha(txtFechaNac.getText().trim())) {
			        		
			        		lblLosDatosIngresados.setText("El Formato de la fecha debe ser dd/MM/yyyy");
			        		
			        	}*/
			        				        	
			        	if(!verificarCaracteres(txtNombre.getText().trim())) {
			        		lblLosDatosIngresados.setText("Nombre no puede contener numeros ni simbolos.");

			        	}
			        	else if(!verificarCaracteres(txtApellido.getText().trim())) {
			        		lblLosDatosIngresados.setText("Apellido no puede contener numeros ni simbolos.");

			        	}
			        	else if(!verificarNumeros(txtTelefono.getText().trim())) {
			        		
			        		lblLosDatosIngresados.setText("El Numero de telefono solo puede contener numeros");
			        		
			        	}
			        	//verificar formato fecha
			        	else if(!verificarFormatoFecha(txtFechaNac.getText().trim())) {
			        		lblLosDatosIngresados.setText("El Formato de la fecha debe ser dd/MM/yyyy");
			        	}
			        	//verificar mes y dia validos 
			        	else if(!verificarDiaYmes(txtFechaNac.getText().trim())) {
			        		lblLosDatosIngresados.setText("Ingrese una fecha vï¿½lida.");
			        	}
			        	//verificar fecha de nacimiento
			        	else if(!verificarFechaNac(txtFechaNac.getText().trim())) {
			        		lblLosDatosIngresados.setText("La fecha de nacimiento no puede ser superior a la actual.");
			        	}
			        	
			        }
			        
			        if(verificarNumeros(txtTelefono.getText().trim()) && verificarFormatoFecha(txtFechaNac.getText().trim())
			        		&& verificarDiaYmes(txtFechaNac.getText().trim()) && verificarFechaNac(txtFechaNac.getText().trim())
			        		&&verificarCaracteres(txtApellido.getText().trim()) && verificarCaracteres(txtNombre.getText().trim())) {
			        	
			        	lblLosDatosIngresados.setText("Los datos ingresados fueron:");
			        	lblResultado1.setVisible(true);
			        	//lblResultado2.setVisible(true);
			        	//lblResultado1.setText(txtNombre.getText() +" " + txtApellido.getText());
			        	//lblResultado2.setText("FdN:" + txtFechaNac.getText() + " Tel:" + txtTelefono.getText());
			        	
			        	lblResultado1.setText(txtNombre.getText()+", "+ txtApellido.getText() + 
			        			", " + txtTelefono.getText()+ ", "+ txtFechaNac.getText());
			        	limpiarCampos();
			        	
			        }			        
			        
			    }
				
			
		});
		
		setVisible(true);
	}
	
	//VERIFICAR QUE SEAN NUMEROS
	public boolean verificarNumeros(String numeros) {
		boolean soloNumeros = (numeros != null && numeros.matches("^[0-9]*$"));
		return soloNumeros;
	}
		
	//VERIFICAR QUE SEA UNA FECHA VALIDA
	public boolean verificarFormatoFecha(String fecha) {
		boolean fechaValida = (fecha != null && fecha.matches("^([0-9]{2})/([0-9]{2})/([0-9]{4})$"));
		return fechaValida;
	}
	
	//VERIFICAR DIA Y MES
	public boolean verificarDiaYmes(String fecha) {		
		String [] fechas=fecha.split("/");
		int dia=Integer.parseInt(fechas[0]);
		int mes=Integer.parseInt(fechas[1]);
		int año=Integer.parseInt(fechas[2]);
		
		if (mes < 1 || mes > 12 || (mes == 4 || mes == 6 || mes == 9 
        		|| mes == 11) && dia > 30 || (mes == 2 && dia > 28) 
        		|| (mes == 2 && dia == 29 && !esBisiesto(año)) 
        		|| (dia < 1 || dia > 31)) {
        	return false;
        }
		else {
			return true;
		}
	}
	
	//VERIFICAR Aï¿½O BISIESTO
	public boolean esBisiesto(int año){
		return (año % 4 == 0 && año % 100 != 0) || (año % 400 == 0);
	}
	
	//VERIFICAR FECHA NACIMIENTO
	public boolean verificarFechaNac(String fecha) {
		LocalDate fechaActual= LocalDate.now();
		int añoActual=fechaActual.getYear();
		int mesActual=fechaActual.getMonthValue();
		int diaActual=fechaActual.getDayOfMonth();
		
		String [] fechas=fecha.split("/");
		int dia=Integer.parseInt(fechas[0]);
		int mes=Integer.parseInt(fechas[1]);
		int año=Integer.parseInt(fechas[2]);
		
        if (año>añoActual || (año==añoActual && mes>mesActual)
        		|| (año==añoActual && mes==mesActual && dia>diaActual)) {
        	
        	return false;
        }
        else {
        	return true;
        }
	}
	
	//VERIFICAR QUE SEAN CARACTERES
 	public boolean verificarCaracteres(String cadena) {
 		return cadena.matches("^[a-zA-Z]+$");
 	}

 	//LIMPIAR FORMULARIO
	public void limpiarCampos(){
	  	txtApellido.setText("");
        txtNombre.setText("");
        txtFechaNac.setText("");
        txtTelefono.setText("");
	}	
	
		
}
