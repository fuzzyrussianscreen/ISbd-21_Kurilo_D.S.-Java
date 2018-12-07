package Lab;

import java.awt.BorderLayout;

import java.awt.Button;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class GUI_Hangar {
	private MultiLevelHangar hangar;
	private final int countLevel = 3;
	
	GUI_Hangar() {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 1317, 637);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		PanelHangar panelHangar = new PanelHangar();
		hangar = new MultiLevelHangar(countLevel, panelHangar.getWidth(), panelHangar.getHeight());
		panelHangar.setHangar(hangar.get(0));
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

		DefaultListModel listModel = new DefaultListModel();
		for (int i = 1; i <= countLevel; i++) {
			listModel.addElement("Уровень " + i);
		}
		JList list = new JList(listModel);
		list.setBounds(1100, 160, 132, 107);
		frame.getContentPane().add(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setSelectedIndex(0);
		list.addListSelectionListener(e -> {
			panelHangar.setHangar(hangar.get(list.getSelectedIndex()));
			panelHangar.repaint();
		});

		JButton buttonTake = new JButton("Забрать");
		buttonTake.addActionListener(e -> {
			int planePosition = Integer.parseInt(textField.getText());
			IAircraft fighter;
			if ((fighter = hangar.get(list.getSelectedIndex()).removeFighter(planePosition)) != null) {
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
			hangar.get(list.getSelectedIndex()).addFighter(fighter);
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
			hangar.get(list.getSelectedIndex()).addFighter(fighter);
			panelHangar.repaint();
		});
		buttonParkSportPlane.setLayout(null);
		buttonParkSportPlane.setBounds(	1183, 26, 107, 36);
		frame.getContentPane().add(buttonParkSportPlane);
		frame.setVisible(true);
	}
}
