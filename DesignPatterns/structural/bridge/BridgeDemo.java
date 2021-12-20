package structural.bridge;

import structural.bridge.abstraction.Circle;
import structural.bridge.abstraction.Shape;
import structural.bridge.abstraction.Square;
import structural.bridge.implementor.BlueColor;
import structural.bridge.implementor.RedColor;

public class BridgeDemo {
	public static void main(String[] args) {
		Shape blueSquare = new Square(new BlueColor());
		blueSquare.draw();
		
		Shape redCircle = new Circle(new RedColor());
		redCircle.draw();
	}
}
