package main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;

public class App {

	 static public void main(String[] args) {
	        
	    ///Ventana
	    Frame frame = new Frame();
	
	    //Componentes
	    JButton button = new Button();
	    JTextField textBox = new TextBox();
	
	    //Agregar componentes a la ventana
	    frame.agregarTextBox(textBox);
	    frame.agregarButton(button);
	
	    //Mostrar ventana
	    frame.cambiarVisibilidad(true);
	     
	}

}
