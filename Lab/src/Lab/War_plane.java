package Lab;

import java.awt.Color;

import java.awt.Graphics;



public abstract class War_plane implements IAircraft{

	protected int _startPosX;
	
	protected int _startPosY;
	
	protected int _pictureWidth;
	
	protected int _pictureHeight;
	
	protected int MaxSpeed;
	void getMaxSpeed(int MaxSpeed) {this.MaxSpeed=MaxSpeed;}
	int setMaxSpeed() {return this.MaxSpeed;}

	protected float Weight;
	void getWeight(int Weight) {this.Weight=Weight;}
	float setWeight() {return this.Weight;}

	protected Color MainColor;
	void getMainColor(Color MainColor) {this.MainColor=MainColor;}
	Color setMainColor() {return this.MainColor;}

	protected Color DopColor;
	void getDopColor(Color DopColor) {this.DopColor=DopColor;}
	Color setDopColorr() {return this.DopColor;}

	public void SetPosition(int x, int y, int width, int height)
	{
		_startPosX = x;
		_startPosY = y;
		_pictureWidth = width;
		_pictureHeight = height;
	}
	
	public abstract void DrawFighter(Graphics g);
	
	public abstract void MoveTransport(Direction direction);
	
}
