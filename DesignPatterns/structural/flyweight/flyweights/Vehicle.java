package structural.flyweight.flyweights;

/**
 * @author MoMi
 *
 */
public abstract class Vehicle {

	private String model;
	private String color;

	public Vehicle(String model, String color) {
		this.model = model;
		this.color = color;
	}

	public String getModel() {
		return model;
	}

	public String getColor() {
		return color;
	}

	@Override
	public String toString() {
		return "a " + color + " " + model;
	}

}
