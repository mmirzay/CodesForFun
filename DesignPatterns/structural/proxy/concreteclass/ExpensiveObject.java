package structural.proxy.concreteclass;

import structural.proxy.interfaces.ExpensiveObjectInterface;

public class ExpensiveObject implements ExpensiveObjectInterface {

	@Override
	public void doSomething() {
		System.out.println("heavy process in concrete class");
	}

}
