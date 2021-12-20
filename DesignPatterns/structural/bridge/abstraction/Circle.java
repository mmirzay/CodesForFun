package structural.bridge.abstraction;

import structural.bridge.implementor.ColorInterface;

public class Circle extends Shape {

	public Circle(ColorInterface color) {
		super(color);
	}

	@Override
	public void draw() {
		System.out.println("Circle with " + getColor().fill() + " is drawn.");
	}

}
