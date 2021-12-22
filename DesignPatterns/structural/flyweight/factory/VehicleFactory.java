package structural.flyweight.factory;

import java.util.HashMap;
import java.util.Map;

import structural.flyweight.flyweights.AudiCar;
import structural.flyweight.flyweights.BenzCar;
import structural.flyweight.flyweights.BmwCar;
import structural.flyweight.flyweights.Vehicle;

public class VehicleFactory {
	private static Map<String, Vehicle> vehicleCache = new HashMap<>();

	public static Vehicle getBmwCar(String color) {
		return vehicleCache.computeIfAbsent(color, c -> new BmwCar(c));
	}

	public static Vehicle getBenzCar(String color) {
		return vehicleCache.computeIfAbsent(color, c -> new BenzCar(c));
	}

	public static Vehicle getAudiCar(String color) {
		return vehicleCache.computeIfAbsent(color, c -> new AudiCar(c));
	}
}
