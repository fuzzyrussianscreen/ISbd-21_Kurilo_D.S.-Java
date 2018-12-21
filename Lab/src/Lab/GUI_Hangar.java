package Lab;

import java.awt.BorderLayout;

import java.awt.Button;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.log4j.Logger;

public class GUI_Hangar {
	private MultiLevelHangar hangar;
	private final int countLevel = 3;
	private GUI_Hangar_Config select;
	private JFrame frame;
	private JList list;

	private Logger logger = Log.getlogger();

	public void getFighter() throws HangarOverflowException {
		select = new GUI_Hangar_Config(frame);
		if (select.res()) {
			IAircraft fighter = select.fighter;
			int place = hangar.get(list.getSelectedIndex()).addFighter(fighter);
			logger.info("Добавлен истребитель " + fighter.getInfo() + " на место " + place);

		}
	}

	GUI_Hangar() {
		frame = new JFrame();
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
		list = new JList(listModel);
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
			try {
				if ((fighter = hangar.get(list.getSelectedIndex()).removeFighter(planePosition)) != null) {
					fighter.SetPosition(0, 60, panelTakeFighter.getWidth(), panelTakeFighter.getHeight());
					panelTakeFighter.setAircraft(fighter);
				} else {
					panelTakeFighter.setAircraft(null);
				}
			} catch (HangarNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			panelTakeFighter.repaint();
			panelHangar.repaint();
		});
		buttonTake.setBounds(11, 80, 120, 30);
		panelMain.add(buttonTake);
		JButton buttonParkSportPlane = new JButton("Fighter");
		buttonParkSportPlane.addActionListener(e -> {
			try {
				getFighter();
			} catch (HangarOverflowException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			panelHangar.repaint();
		});
		buttonParkSportPlane.setLayout(null);
		buttonParkSportPlane.setBounds(	1183, 26, 107, 36);
		frame.getContentPane().add(buttonParkSportPlane);
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);
		JMenuItem menuSave = new JMenuItem("Save");
		menuSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filesave = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt file", "txt");
				filesave.setFileFilter(filter);
				if (filesave.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = filesave.getSelectedFile();
					String path = file.getAbsolutePath()+".txt";
					if (hangar.SaveData(path)) {
						JOptionPane.showMessageDialog(null, "Saved");
						return;
					} else {
						JOptionPane.showMessageDialog(null, "Save failed", "", 0, null);
					}
				}
			}
		});
		menuFile.add(menuSave);
		JMenuItem menuLoad = new JMenuItem("Load");
		menuLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt file", "txt");
				fileChooser.setFileFilter(filter);
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						if (hangar.load(file.getAbsolutePath())) {
							JOptionPane.showMessageDialog(null, "Loaded");
							logger.info("Загружено из файла " + file);
						} else {
							JOptionPane.showMessageDialog(null, "Load failed", "", 0, null);
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "", 0, null);
					}
				}
				panelHangar.setHangar(hangar.get(list.getSelectedIndex()));
				panelHangar.repaint();
			}
		});
		menuFile.add(menuLoad);

		frame.setVisible(true);
	}
}
