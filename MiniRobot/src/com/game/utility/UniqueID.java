package com.game.utility;

/**
 * @author MoMi
 *
 */
public class UniqueID {
	private int id;

	private UniqueID(int id) {
		this.id = id;
	}

	public static UniqueID of(int id) {
		return new UniqueID(id);
	}

	public int getID() {
		return this.id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UniqueID other = (UniqueID) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "" + id;
	}

}
