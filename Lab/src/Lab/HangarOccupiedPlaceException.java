package Lab;

public class HangarOccupiedPlaceException extends Exception {
	public HangarOccupiedPlaceException(int i) {
		super("На месте " + i + " уже стоит истребитель");
	}
}
