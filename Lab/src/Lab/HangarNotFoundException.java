package Lab;

public class HangarNotFoundException extends Exception {
	public HangarNotFoundException(int i) {
		super("Не найден истребитель по месту " + i);
	}
}
