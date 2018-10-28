import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;


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

	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 900, 518);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);


		Button button = new Button("New button");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random rnd = new Random();
				System.out.println("Кнопка нажата");
				fighter = new Fighter(rnd.nextInt(200)+300, rnd.nextInt(1000)+1000, Color.BLACK, Color.GRAY, true, true, true);
				fighter.SetPosition(rnd.nextInt(100)+50, rnd.nextInt(100)+50, panel.WIDTH, panel.HEIGHT);
				panel = new MyPanel(fighter);
				panel.setBounds(20, 20, 225, 201);
				frame.getContentPane().add(panel);
				panel.repaint();;
				
			}
		});
		button.setBounds(767, 347, 79, 24);
		frame.getContentPane().add(button);
		
	}
}
