package creational.singleton;

//standard method: not early, no synchronization need
public class BillPughSingleton {

	private BillPughSingleton() {
	}

	private static class InstanceHolder {
		private final static BillPughSingleton INSTANCE = new BillPughSingleton();
	}

	// must be synchronized
	public static synchronized BillPughSingleton getInstance() {
		return InstanceHolder.INSTANCE;
	}
}
