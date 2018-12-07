package Lab;
import java.awt.Graphics;
import javax.swing.JPanel;

public class PanelHangar extends JPanel {

	Hangar<IAircraft> hangar;

	public void setHangar(Hangar<IAircraft> hangar2) {
		hangar=hangar2;
	}

	public void paint(Graphics g) {
		super.paint(g);
		hangar.DrawHangar(g);
	}
}
