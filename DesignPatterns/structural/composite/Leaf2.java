package structural.composite;

public class Leaf2 implements Component {

	@Override
	public void printName() {
		System.out.println(getClass().getSimpleName());
	}

}
