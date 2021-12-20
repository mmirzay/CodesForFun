package creational.singleton;

public class EarlySingleton {
	private static final EarlySingleton INSTANCE = new EarlySingleton();

	private EarlySingleton() {
	}

	public static EarlySingleton getInstance() {
		return INSTANCE;
	}
}
