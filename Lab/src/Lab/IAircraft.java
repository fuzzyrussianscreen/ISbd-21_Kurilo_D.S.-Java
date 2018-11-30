package Lab;

import java.awt.Graphics;

public interface IAircraft {
	void SetPosition(int x, int y, int width, int height);
    void MoveTransport(Direction direction);
    void DrawFighter(Graphics g);
}
