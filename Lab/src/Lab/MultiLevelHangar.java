package Lab;

import java.util.ArrayList;

public class MultiLevelHangar {

	ArrayList<Hangar<IAircraft>> hangarStages;
	private final int countPlaces = 16;
	public MultiLevelHangar(int countStages, int pictureWidth, int pictureHeight)
	{
		hangarStages = new ArrayList<Hangar<IAircraft>>();
		for (int i = 0; i < countStages; ++i)
		{
			hangarStages.add(new Hangar<IAircraft>(countPlaces, pictureWidth, pictureHeight));
		}
	}
	public Hangar<IAircraft> get(int ind)
	{
		if (ind > -1 && ind < hangarStages.size())
		{
			return hangarStages.get(ind);
		}
		return null;
	}
}
