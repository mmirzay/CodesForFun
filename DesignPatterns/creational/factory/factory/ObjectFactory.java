package creational.factory.factory;

import creational.factory.exceptions.UnknownTypeException;
import creational.factory.objects.ObjectInterface;
import creational.factory.objects.ObjectTypeOne;
import creational.factory.objects.ObjectTypeThree;
import creational.factory.objects.ObjectTypeTwo;

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
