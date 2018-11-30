package Lab;
import java.awt.Graphics;

import javax.swing.JPanel;


public class PanelHungar extends JPanel {

	Fighter fighter;

	public PanelHungar(Fighter fighter2) {
		fighter=fighter2;
	}

	public void paint(Graphics g) {
		super.paint(g);
		fighter.DrawFighter(g);
	}
}
