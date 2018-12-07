package Lab;

import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelTakeFighter extends JPanel {

	IAircraft fighter;

	public void setAircraft(IAircraft fighter2) {
		fighter=fighter2;
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (fighter != null) {
			fighter.DrawFighter(g);
		}
	}
}
