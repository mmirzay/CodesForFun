package structural.adaptor.adaptor;

import structural.adaptor.Adaptee.Rectangle;
import structural.adaptor.Adaptee.RectangleAreaCalculator;
import structural.adaptor.target.Triangle;

//if this class extends adaptee and override its method, it is class adaptor
public class TriangleAreaCalculator {
	private RectangleAreaCalculator calculator; // this is object adaptor

	public TriangleAreaCalculator(RectangleAreaCalculator calculator) {
		this.calculator = calculator;
	}

	public double getArea(Triangle triangle) {
		Rectangle rectangle = new Rectangle();
		rectangle.length = triangle.base;
		rectangle.width = triangle.height * 0.5;
		return calculator.getArea(rectangle);
	}
}
