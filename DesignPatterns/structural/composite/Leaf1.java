package structural.composite;

public class Leaf1 implements Component {

	@Override
	public void printName() {
		System.out.println(getClass().getSimpleName());
	}

}
