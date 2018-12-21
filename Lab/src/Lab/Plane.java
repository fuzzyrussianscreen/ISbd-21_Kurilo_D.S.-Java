package Lab;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;


public class Plane extends War_plane {

	protected  int planeWidth = 100;
	protected  int planeHeight = 60;

	protected boolean MiddleWild;
	void getMiddleWild(boolean MiddleWild) {this.MiddleWild=MiddleWild;}
	boolean setMiddleWildt() {return this.MiddleWild;}

	protected boolean Backwild;
	void getBackWild(boolean BackWild) {this.Backwild=BackWild;}
	boolean setBackWild() {return this.Backwild;}

	public Plane(int maxSpeed, float weight, Color mainColor, boolean middleWild, boolean backWild)
	{
		MaxSpeed = maxSpeed;
		Weight = weight;
		MainColor = mainColor;
		MiddleWild = middleWild;
		Backwild = backWild;
	}

	@Override
	public void MoveTransport(Direction direction)
	{
		float step = MaxSpeed * 100 / Weight;
		switch (direction)
		{
		// вправо
		case Right:
			if (_startPosX + step < _pictureWidth - planeWidth)
			{
				_startPosX += step;
			}
			break;
			//влево
		case Left:
			if (_startPosX - step > 0)
			{
				_startPosX -= step;
			}
			break;
			//вверх
		case Up:
			if (_startPosY - step > 0)
			{
				_startPosY -= step;
			}
			break;
			//вниз
		case Down:
			if (_startPosY + step < _pictureHeight - planeHeight)
			{
				_startPosY += step;
			}
			break;
		}
	}
	
	@Override
	public void DrawFighter(Graphics gr)
	{

		Graphics2D g = (Graphics2D)gr;
		Stroke oldStroke = g.getStroke();
		float[] shtrich = {14, 5};
		BasicStroke bs = new BasicStroke(8, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 3, shtrich, 0);
		g.setStroke(bs);
		
		if (Backwild)
		{
			g.setColor(MainColor);
			g.drawLine( _startPosX + 31, _startPosY - 24, _startPosX + 31, _startPosY + 24);
			g.setColor(DopColor);
			g.drawLine( _startPosX + 4, _startPosY - 5, _startPosX, _startPosY - 20);
			g.drawLine( _startPosX + 14, _startPosY - 5, _startPosX, _startPosY - 20);
			g.drawLine( _startPosX + 4, _startPosY + 5, _startPosX, _startPosY + 20);
			g.drawLine( _startPosX + 14, _startPosY + 5, _startPosX, _startPosY + 20);
		}
		if (MiddleWild)
		{
			g.setColor(DopColor);
			g.drawLine( _startPosX + 24, _startPosY - 5, _startPosX + 22, _startPosY - 50);
			g.drawLine( _startPosX + 40, _startPosY - 5, _startPosX + 22, _startPosY - 50);
			g.drawLine( _startPosX + 24, _startPosY + 5, _startPosX + 22, _startPosY + 50);
			g.drawLine( _startPosX + 40, _startPosY + 5, _startPosX + 22, _startPosY + 50);
		}

		g.setColor(DopColor);
		g.fillRect( _startPosX, _startPosY - 6, 80, 12);
		g.setColor(MainColor);
		g.drawLine( _startPosX + 79, _startPosY - 3, _startPosX + 100, _startPosY);
		g.drawLine( _startPosX + 79, _startPosY + 3, _startPosX + 100, _startPosY);
		
		g.drawLine( _startPosX + 100, _startPosY, _startPosX + 110, _startPosY);
	}
	
	public void SetMainColor(Color color) {
        MainColor = color;
    }
}
