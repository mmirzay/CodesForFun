package creational.singleton;

public class LazySingleton {
	private static LazySingleton instance;

	private LazySingleton() {
	}

	// must be synchronized
	public static synchronized LazySingleton getInstance() {
		if (instance == null)
			instance = new LazySingleton();
		return instance;
	}
}
