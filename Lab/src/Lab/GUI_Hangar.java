package Lab;

import java.awt.BorderLayout;

import java.awt.Button;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI_Hangar {
	private Hangar<IAircraft> hangar;

	GUI_Hangar() {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 1317, 637);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		PanelHangar panelHangar = new PanelHangar();
		hangar = new Hangar<>(16, panelHangar.getWidth(), panelHangar.getHeight());
		panelHangar.setHangar(hangar);
		panelHangar.setBounds(1, 1, 1065, 590);
		frame.getContentPane().add(panelHangar);

		JPanel panelMain = new JPanel();
		panelMain.setBounds(1118, 287, 170, 271);
		frame.getContentPane().add(panelMain);
		panelMain.setLayout(null);

		JLabel label = new JLabel("Забрать");
		label.setBounds(10, 11, 72, 14);
		panelMain.add(label);

		JLabel lblNewLabel = new JLabel("самолет");
		lblNewLabel.setBounds(10, 27, 93, 14);
		panelMain.add(lblNewLabel);

		JLabel label_1 = new JLabel("Место:");
		label_1.setBounds(10, 55, 46, 14);
		panelMain.add(label_1);

		JTextField textField = new JTextField();
		textField.setBounds(55, 52, 67, 20);
		panelMain.add(textField);
		textField.setColumns(10);

		PanelTakeFighter panelTakeFighter = new PanelTakeFighter();
		panelTakeFighter.setBounds(10, 150, 170, 170);
		panelMain.add(panelTakeFighter);

		JButton buttonTake = new JButton("Забрать");
		buttonTake.addActionListener(e -> {
			int planePosition = Integer.parseInt(textField.getText());
			IAircraft fighter;
			if ((fighter = hangar.removeFighter(planePosition)) != null) {
				fighter.SetPosition(0, 60, panelTakeFighter.getWidth(), panelTakeFighter.getHeight());
				panelTakeFighter.setAircraft(fighter);
			} else {
				panelTakeFighter.setAircraft(null);
			}
			panelTakeFighter.repaint();
			panelHangar.repaint();
		});
		buttonTake.setBounds(11, 80, 120, 30);
		panelMain.add(buttonTake);

		JButton buttonParkPlane = new JButton("Plane");
		buttonParkPlane.addActionListener(e -> {
			Color mainColor = JColorChooser.showDialog(null, "Choose a color", Color.GRAY);
			IAircraft fighter = new Plane(300, 1000, mainColor, true, true);
			hangar.addFighter(fighter);
			panelHangar.repaint();
		});
		buttonParkPlane.setLayout(null);
		buttonParkPlane.setBounds(1183, 68, 107, 36);
		frame.getContentPane().add(buttonParkPlane);

		JButton buttonParkSportPlane = new JButton("Fighter");
		buttonParkSportPlane.addActionListener(e -> {
            Color mainColor = JColorChooser.showDialog(null, "Choose a color", Color.GRAY);
			Color dopColor = JColorChooser.showDialog(null, "Choose a dopcolor", Color.GRAY);
			IAircraft fighter = new Fighter(600, 1000, mainColor, dopColor, true, true, true, true, true);
			hangar.addFighter(fighter);
			panelHangar.repaint();
		});
		buttonParkSportPlane.setLayout(null);
		buttonParkSportPlane.setBounds(	1183, 26, 107, 36);
		frame.getContentPane().add(buttonParkSportPlane);
		frame.setVisible(true);
	}
}
