package structural.flyweight;

import structural.flyweight.factory.VehicleFactory;
import structural.flyweight.flyweights.Vehicle;

public class FlyweightDemo {
	public static void main(String[] args) {
		Vehicle redBmw1 = VehicleFactory.getBmwCar("red");
		Vehicle redBmw2 = VehicleFactory.getBmwCar("red");
		Vehicle blueAudi = VehicleFactory.getAudiCar("blue");

		System.out.println(redBmw1 + " hashCode: " + redBmw1.hashCode());
		System.out.println(redBmw2 + " hashCode: " + redBmw2.hashCode());
		System.out.println("two BMW are same: " + (redBmw1 == redBmw2));
		System.out.println(blueAudi + " hashCode: " + blueAudi.hashCode());

	}
}
