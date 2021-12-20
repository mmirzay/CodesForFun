package structural.bridge.abstraction;

import structural.bridge.implementor.ColorInterface;

public abstract class Shape {
	private ColorInterface color;

	public Shape(ColorInterface color) {
		this.setColor(color);
	}

	abstract public void draw();

	public ColorInterface getColor() {
		return color;
	}

	public void setColor(ColorInterface color) {
		this.color = color;
	}
}
