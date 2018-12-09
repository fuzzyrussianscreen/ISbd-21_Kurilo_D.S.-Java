package Lab;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.HashMap;

public class Hangar<T extends IAircraft> {

	public HashMap<Integer, T> _places;
	public int _maxCount;
	protected int PictureWidth;
	void getPictureWidth(int PictureWidth) {this.PictureWidth=PictureWidth;}
	int setPictureWidth() {return this.PictureWidth;}
	
	protected int PictureHeight;
	void getPictureHeight(int PictureHeight) {this.PictureHeight=PictureHeight;}
	int setPictureHeight() {return this.PictureHeight;}
	
	private int _placeSizeWidth = 260;
	private int _placeSizeHeight = 120;
	
	public Hangar(int sizes, int pictureWidth, int pictureHeight)
	{
		_maxCount = sizes;
		_places = new HashMap<Integer, T>();
		PictureWidth = pictureWidth;
		PictureHeight = pictureHeight;
	}
	public int addFighter(T fighter)
	{
		if (_places.size() == _maxCount) {
            return -1;
        }
		for (int i = 0; i < _maxCount; i++)
		{
			if (CheckFreePlace(i))
			{
				_places.put(i,fighter);
				_places.get(i).SetPosition(5 + i / 4 * _placeSizeWidth + 5, i % 4 * _placeSizeHeight + 60, PictureWidth, PictureHeight);
				return i;
			}
		}
		return -1;
	}
	public T removeFighter(int index)
	{
		index -= 1;
		if (!CheckFreePlace(index))
		{
			T fighter = _places.get(index);
			_places.remove(index);
			return fighter;
		}
		return null;
	}
	private boolean CheckFreePlace(int index)
	{
		return !_places.containsKey(index);
	}
	public void DrawHangar(Graphics g)
	{
		DrawMarking(g);
		for (int i = 0; i < _places.size(); i++)
		{
			if (!CheckFreePlace(i))
			{
				_places.get(i).DrawFighter(g);
			}
		}
	}
	private void DrawMarking(Graphics g2)
	{
		Graphics2D g = (Graphics2D)g2;
		Stroke oldStroke = g.getStroke();
		float[] shtrich = {14, 3};
		BasicStroke bs = new BasicStroke(8, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 3, shtrich, 0);
		g.setStroke(bs);
		
		g.setColor(Color.GRAY);
		g.fillRect( 0, 0, (_places.size() / 4) * _placeSizeWidth + 10, 600);
		for (int i = 0; i < _places.size() / 4; i++)
		{
			for (int j = 0; j < 5; ++j)
			{
				g.setColor(Color.BLACK);
				g.drawLine( i * _placeSizeWidth, j * _placeSizeHeight, i * _placeSizeWidth + 120, j * _placeSizeHeight);
				g.setColor(Color.WHITE);
				g.drawLine( i * _placeSizeWidth + 80, j * _placeSizeHeight + 60, i * _placeSizeWidth + 190, j * _placeSizeHeight + 60);
			}
			g.setColor(Color.BLACK);
			g.drawLine( i * _placeSizeWidth, 0, i * _placeSizeWidth, 480);
			g.setColor(Color.WHITE);
			g.drawLine( i * _placeSizeWidth + 190, 60, i * _placeSizeWidth + 190, 540);
			g.drawLine( i * _placeSizeWidth, 540, i * _placeSizeWidth + 260, 540);
		}
		
	}
}

