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

public class Fighter {

	public enum Direction
	{
		Up,
		Down,
		Left,
		Right
	}

	private int _startPosX;
	/// <summary>
	/// Правая кооридната отрисовки
	/// </summary>
	private int _startPosY;
	/// <summary>
	/// Ширина окна отрисовки
	/// </summary>
	private int _pictureWidth;
	//Высота окна отрисовки
	private int _pictureHeight;
	/// <summary>
	/// Ширина отрисовки 
	/// </summary>
	private final int carWidth = 100;
	/// <summary>
	/// Ширина отрисовки 
	/// </summary>
	private final int carHeight = 60;

	private int MaxSpeed;
	void getMaxSpeed(int MaxSpeed) {this.MaxSpeed=MaxSpeed;}
	int setMaxSpeed() {return this.MaxSpeed;}

	private int Weight;
	void getWeight(int Weight) {this.Weight=Weight;}
	int setWeight() {return this.Weight;}

	private Color MainColor;
	void getMainColor(Color MainColor) {this.MainColor=MainColor;}
	Color setMainColor() {return this.MainColor;}

	private Color DopColor;
	void getDopColor(Color DopColor) {this.DopColor=DopColor;}
	Color setDopColorr() {return this.DopColor;}

	private boolean FrontSpoiler;
	void getFrontSpoiler(boolean FrontSpoiler) {this.FrontSpoiler=FrontSpoiler;}
	boolean setFrontSpoilert() {return this.FrontSpoiler;}

	private boolean MiddleSpoiler;
	void getMiddleSpoiler(boolean MiddleSpoiler) {this.MiddleSpoiler=MiddleSpoiler;}
	boolean setMiddleSpoilert() {return this.MiddleSpoiler;}

	private boolean BackSpoiler;
	void getBackSpoiler(boolean BackSpoiler) {this.BackSpoiler=BackSpoiler;}
	boolean setBackSpoiler() {return this.BackSpoiler;}

	public Fighter(int maxSpeed, int weight, Color mainColor, Color dopColor, boolean
			frontSpoiler, boolean middleSpoiler, boolean backSpoiler)
	{
		MaxSpeed = maxSpeed;
		Weight = weight;
		MainColor = mainColor;
		DopColor = dopColor;
		MiddleSpoiler = middleSpoiler;
		FrontSpoiler = frontSpoiler;
		BackSpoiler = backSpoiler;
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
			if (_startPosX + step < _pictureWidth - carWidth)
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
			if (_startPosY + step < _pictureHeight - carHeight)
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
	public void DrawCar(Graphics gr)
	{
		
		Graphics2D g = (Graphics2D)gr;
		Stroke oldStroke = g.getStroke();
		float[] shtrich = {14, 5};
		BasicStroke bs = new BasicStroke(8, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 3, shtrich, 0);
		g.setStroke(bs);

		if (BackSpoiler)
		{
			g.setColor(MainColor);
			g.drawLine( _startPosX + 31, _startPosY - 24, _startPosX + 31, _startPosY + 24);
			g.setColor(DopColor);
			g.drawLine( _startPosX + 4, _startPosY - 5, _startPosX, _startPosY - 20);
			g.drawLine( _startPosX + 14, _startPosY - 5, _startPosX, _startPosY - 20);
			g.drawLine( _startPosX + 4, _startPosY + 5, _startPosX, _startPosY + 20);
			g.drawLine( _startPosX + 14, _startPosY + 5, _startPosX, _startPosY + 20);
		}
		if (MiddleSpoiler)
		{
			g.setColor(DopColor);
			g.drawLine( _startPosX + 24, _startPosY - 5, _startPosX + 22, _startPosY - 50);
			g.drawLine( _startPosX + 40, _startPosY - 5, _startPosX + 22, _startPosY - 50);
			g.drawLine( _startPosX + 24, _startPosY + 5, _startPosX + 22, _startPosY + 50);
			g.drawLine( _startPosX + 40, _startPosY + 5, _startPosX + 22, _startPosY + 50);

			g.drawLine( _startPosX + 46, _startPosY , _startPosX + 55, _startPosY + 10);
			g.drawLine( _startPosX + 46, _startPosY , _startPosX + 55, _startPosY - 10);
		}
		g.setColor(Color.BLACK);
		g.fillRect( _startPosX, _startPosY - 6, 80, 12);
		g.setColor(MainColor);
		g.drawLine( _startPosX + 79, _startPosY - 3, _startPosX + 100, _startPosY);
		g.drawLine( _startPosX + 79, _startPosY + 3, _startPosX + 100, _startPosY);
		if (FrontSpoiler)
		{
			g.setColor(DopColor);
			g.drawLine( _startPosX + 100, _startPosY, _startPosX + 110, _startPosY);
		}
	}
}
