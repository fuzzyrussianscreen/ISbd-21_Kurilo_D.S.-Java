import java.awt.Graphics;

import javax.swing.JPanel;


public class MyPanel extends JPanel {

	Fighter fighter;

	public MyPanel(Fighter fighter2) {
		fighter=fighter2;
	}

	public void paint(Graphics g) {
		super.paint(g);
		fighter.DrawCar(g);
	}
}
