package structural.proxy.proxyclass;

import structural.proxy.concreteclass.ExpensiveObject;
import structural.proxy.interfaces.ExpensiveObjectInterface;

public class ExpensiveObjectProxy implements ExpensiveObjectInterface {
	private static ExpensiveObjectInterface subject;

	@Override
	public void doSomething() {
		if (checkAccess()) {
			if (subject == null) // lazy initialization
				subject = new ExpensiveObject();
			subject.doSomething();
		}
	}

	private boolean checkAccess() {
		return true;
	}

}
