package creational.factory.factory;

import creational.factory.exceptions.UnknownTypeException;

//this is abstract factory which must different factories implement it
public interface Factory<TYPE, OBJECT> {
	OBJECT getObject(TYPE type) throws UnknownTypeException;
}
