package Lab;
import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Panel;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class Fighter extends Plane {

	private boolean FrontWind;
	void getFrontWind(boolean FrontWind) {this.FrontWind=FrontWind;}
	boolean setFrontWindt() {return this.FrontWind;}

	private boolean Signs;
	void getSigns(boolean Signs) {this.Signs=Signs;}
	boolean setSigns() {return this.Signs;}

	private boolean Signs2;
	void getSigns2(boolean Signs2) {this.Signs2=Signs2;}
	boolean setSigns2() {return this.Signs2;}
	public Fighter(int maxSpeed, int weight, Color mainColor, Color dopColor, boolean
			frontWind, boolean middleWind, boolean backWind, boolean signs, boolean signs2)
	{
		super (maxSpeed, weight, mainColor, middleWind, backWind);

		DopColor = dopColor;
		FrontWind = frontWind;
		Signs = signs;
		Signs2 = signs2;
	}
	
	public Fighter(String info) {
		super(info);
		String[] str = info.split(";");
		if (str.length == 12) {
			MaxSpeed = Integer.parseInt(str[0]);
			Weight = Float.parseFloat(str[1]);
			MainColor = new Color(Integer.parseInt(str[2]), Integer.parseInt(str[3]), Integer.parseInt(str[4]));
			DopColor = new Color(Integer.parseInt(str[5]), Integer.parseInt(str[6]), Integer.parseInt(str[7]));
			MiddleWild = Boolean.parseBoolean(str[8]);
			Backwild = Boolean.parseBoolean(str[9]);
			Signs = Boolean.parseBoolean(str[10]);
			Signs2 = Boolean.parseBoolean(str[11]);
		}
	}
	
	public void SetPosition(int x, int y, int width, int height)
	{
		_startPosX = x;
		_startPosY = y;
		_pictureWidth = width;
		_pictureHeight = height;
	}
	
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
	/// <summary>
	/// Отрисовка
	/// </summary>
	/// <param name="g"></param>
	public void DrawFighter(Graphics gr)
	{
		
		Graphics2D g = (Graphics2D)gr;
		Stroke oldStroke = g.getStroke();
		float[] shtrich = {14, 5};
		BasicStroke bs = new BasicStroke(8, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 3, shtrich, 0);
		g.setStroke(bs);

		if (FrontWind){
			g.setColor(DopColor);
			g.drawLine( _startPosX + 46, _startPosY , _startPosX + 55, _startPosY + 10);
			g.drawLine( _startPosX + 46, _startPosY , _startPosX + 55, _startPosY - 10);
		}
		super.DrawFighter(g);
		g.setColor(Color.RED);
		if (Signs) {
			g.fillOval( _startPosX + 18, _startPosY - 52, 10, 12);
			g.fillOval( _startPosX + 18, _startPosY + 40, 10, 12);
		}
		if (Signs) {
			g.fillOval( _startPosX, _startPosY-6, 10, 12);
		}
	}
	
	public void SetDopColor(Color color)
    {
        DopColor = color;
    }
	
	public String getInfo() {
		return MaxSpeed + ";" + Weight + ";" + MainColor.getRed() + ";" + MainColor.getGreen() + ";" + MainColor.getBlue() + ";" 
				+ DopColor.getRed() + ";" + DopColor.getGreen() + ";"+ DopColor.getBlue() + ";" 
				+ MiddleWild + ";" + Backwild + ";" + Signs + ";" + Signs2;
	}
}
