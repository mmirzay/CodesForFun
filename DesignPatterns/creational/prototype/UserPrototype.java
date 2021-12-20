package creational.prototype;

public class UserPrototype implements Cloneable {
	private int immutableField;

	private MutableField mutableField;

	public UserPrototype(int immutableField) {
	}

	@Override
	protected UserPrototype clone() {
		UserPrototype clone = null;
		try {
			clone = (UserPrototype) super.clone();
		} catch (CloneNotSupportedException e) {
			clone = new UserPrototype(this.getImmutableField());
		}

		clone.mutableField = getMutableField().clone();
		return clone;
	}

	public int getImmutableField() {
		return immutableField;
	}

	public MutableField getMutableField() {
		return mutableField;
	}
}

class MutableField {
	@Override
	protected MutableField clone() {
		try {
			return (MutableField) super.clone();
		} catch (CloneNotSupportedException e) {
			return new MutableField();
		}
	}
}
