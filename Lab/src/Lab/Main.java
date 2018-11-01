package Lab;
import java.awt.Button;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Lab.Fighter.Direction;


public class Main {

	private JFrame frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Main() {

		initialize();
	}
	
	Fighter fighter;
	private JPanel panel;
	private JButton buttonLeft;

	
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 958, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);


		Button button = new Button("New button");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random rnd = new Random();
				System.out.println("Кнопка нажата");
				fighter = new Fighter(rnd.nextInt(200)+300, rnd.nextInt(1000)+1000, Color.BLACK, Color.GRAY, true, true, true);
				panel = new MyPanel(fighter);
				panel.setBounds(20, 20, 700, 401);
				frame.getContentPane().add(panel);
				fighter.SetPosition(rnd.nextInt(100)+50, rnd.nextInt(100)+50, panel.getWidth(), panel.getHeight());
				panel.repaint();
				
			}
		});
		button.setBounds(851, 10, 79, 24);
		frame.getContentPane().add(button);
		
		buttonLeft = new JButton("");
		buttonLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fighter.MoveTransport(Direction.Left);
				panel.repaint();
			}
		});
		buttonLeft.setVerticalAlignment(SwingConstants.BOTTOM);
		buttonLeft.setIconTextGap(2);
		
		buttonLeft.setIcon(new ImageIcon("D:\\ФИСТ ИСЭбд\\Тех. программирование\\ISbd-21_Kurilo_D.S-java\\Lab\\arrow1.png"));
		//btnNewButton.setIco
		buttonLeft.setBounds(756, 490, 50, 50);
		frame.getContentPane().add(buttonLeft);
		
		JButton buttonDown = new JButton("");
		buttonDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fighter.MoveTransport(Direction.Down);
				panel.repaint();
			}
		});
		buttonDown.setIcon(new ImageIcon("D:\\ФИСТ ИСЭбд\\Тех. программирование\\ISbd-21_Kurilo_D.S-java\\Lab\\arrow4.png"));
		buttonDown.setVerticalAlignment(SwingConstants.BOTTOM);
		buttonDown.setIconTextGap(2);
		buttonDown.setBounds(818, 490, 50, 50);
		frame.getContentPane().add(buttonDown);
		
		JButton buttonUp = new JButton("");
		buttonUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fighter.MoveTransport(Direction.Up);
				panel.repaint();
			}
		});
		buttonUp.setIcon(new ImageIcon("D:\\ФИСТ ИСЭбд\\Тех. программирование\\ISbd-21_Kurilo_D.S-java\\Lab\\arrow3.png"));
		buttonUp.setVerticalAlignment(SwingConstants.BOTTOM);
		buttonUp.setIconTextGap(2);
		buttonUp.setBounds(818, 427, 50, 50);
		frame.getContentPane().add(buttonUp);
		
		JButton buttonRight = new JButton("");
		buttonRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fighter.MoveTransport(Direction.Right);
				panel.repaint();
			}
		});
		buttonRight.setIcon(new ImageIcon("D:\\ФИСТ ИСЭбд\\Тех. программирование\\ISbd-21_Kurilo_D.S-java\\Lab\\arrow2.png"));
		buttonRight.setVerticalAlignment(SwingConstants.BOTTOM);
		buttonRight.setIconTextGap(2);
		buttonRight.setBounds(880, 490, 50, 50);
		frame.getContentPane().add(buttonRight);
		
	}
}
