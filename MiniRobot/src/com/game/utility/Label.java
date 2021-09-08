package com.game.utility;

public class Label {
	UniqueID id;
	private String name;

	public Label(int id) {
		this.id = UniqueID.of(id);
		this.setName(Constants.DEFAULT_NAME);
	}

	public Label(int id, String name) {
		this.id = UniqueID.of(id);
		this.setName(name);
	}

	@Override
	public String toString() {
		return "ID:" + id.toString() + " - Name:" + getName();
	}

	private String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
