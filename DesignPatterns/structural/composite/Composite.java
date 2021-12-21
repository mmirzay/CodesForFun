package structural.composite;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {

	private List<Component> components = new ArrayList<>();

	public void addComponent(Component component) {
		components.add(component);
	}

	public boolean removeComponent(Component component) {
		return components.remove(component);
	}

	@Override
	public void printName() {
		components.forEach(Component::printName);
	}

}
