package structural.bridge.abstraction;

import structural.bridge.implementor.ColorInterface;

public class Square extends Shape {

	public Square(ColorInterface color) {
		super(color);
	}

	@Override
	public void draw() {
		System.out.println("Square with " + getColor().fill() + " is drawn.");
	}

}
