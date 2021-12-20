package creational.factory.factory;

import creational.factory.exceptions.UnknownTypeException;

public interface Factory<TYPE, OBJECT> {
	OBJECT getObject(TYPE type) throws UnknownTypeException;
}
