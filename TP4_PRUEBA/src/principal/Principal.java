package principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ejercicio_1.Frame_1;
import ejercicio_2.Frame_2;
import ejercicio_3.Frame_3;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;

	//MAIN
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() {
		//JFRAME
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		//PANEL PRINCIPAL
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//PANEL CONTENEDOR BOTONES
		JPanel panel_central = new JPanel();
		contentPane.add(panel_central, BorderLayout.CENTER);
		panel_central.setLayout(null);
		
		//BOTON EJERCICIO 1
		JButton btn_ejercicio_1 = new JButton("Ejercicio 1");
		//// EVENTO EJERCICIO 1
		btn_ejercicio_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Frame_1 frame_1 = new Frame_1();
				frame_1.visibilidadFrame(true);
			}
		});
		btn_ejercicio_1.setBounds(168, 35, 110, 25);
		btn_ejercicio_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_central.add(btn_ejercicio_1);
		
		//BOTON EJERCICIO 2
		JButton btn_ejercicio_2 = new JButton("Ejercicio 2");
		//// EVENTO EJERCICIO 2
		btn_ejercicio_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Frame_2 frame_2 = new Frame_2();
				frame_2.visibilidadFrame(true);
			}
		});
		btn_ejercicio_2.setBounds(168, 79, 110, 25);
		btn_ejercicio_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_central.add(btn_ejercicio_2);
		
		//BONTON EJERCICIO 3
		JButton btn_ejercicio_3 = new JButton("Ejercicio 3");
		//// EVENTO EJERCICIO 3
			btn_ejercicio_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Frame_3 frame_3 = new Frame_3();
					frame_3.visibilidadFrame(true);
				}
			});
		btn_ejercicio_3.setBounds(168, 121, 110, 25);
		btn_ejercicio_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_central.add(btn_ejercicio_3);
		
		//PANEL TITULO
		JPanel panel_titulo = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_titulo.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_titulo, BorderLayout.NORTH);
		
		//TITULO
		JLabel lblGrupoNroX = new JLabel("GRUPO NRO: X");
		lblGrupoNroX.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_titulo.add(lblGrupoNroX);
	}
}
