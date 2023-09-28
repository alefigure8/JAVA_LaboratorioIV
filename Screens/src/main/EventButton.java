package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class EventButton implements ActionListener {
	private JTextField textbox;

    public EventButton(JTextField textbox) {
        this.textbox = textbox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Hola " + this.textbox.getText());
    }
}

