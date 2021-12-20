package creational.factory.factory;

import creational.factory.exceptions.UnknownTypeException;
import creational.factory.objects.ObjectInterface;
import creational.factory.objects.ObjectTypeOne;
import creational.factory.objects.ObjectTypeThree;
import creational.factory.objects.ObjectTypeTwo;

// this is single factory, if doesn't implement super factory, so it called Factory Method
// but with implementing super factory, it will be Abstract Factory Method.
public class ObjectFactory implements Factory<String, ObjectInterface> {

	@Override
	public ObjectInterface getObject(String type) throws UnknownTypeException {
		return switch (type) {
		case "Type1" -> new ObjectTypeOne();
		case "Type2" -> new ObjectTypeTwo();
		case "Type3" -> new ObjectTypeThree();
		default -> throw new UnknownTypeException("no such object");
		};
	}

}
