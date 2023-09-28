package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Frame extends JFrame{

	private JTextField textbox;
    private JButton button;
    private static final long serialVersionUID = 1L;
    
    public Frame(){
        this.setTitle("Ventana");
        this.setBounds(500, 200, 500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
    }

    public void cambiarVisibilidad(Boolean visibilidad){
        this.setVisible(visibilidad);
    }

    public void agregarButton(JButton button){
        this.button = button;
        this.button.addActionListener(new EventButton(this.textbox));
        this.getContentPane().add(this.button);
    }

    public void agregarTextBox(JTextField textBox){
        this.textbox = textBox;
        this.getContentPane().add(this.textbox);
    }

}

