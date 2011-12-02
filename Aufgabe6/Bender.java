public class Bender extends Robot {
	private int rotations;
	
	public Bender(int id) {
		super(id);
	}
	
	public void incRotations(int delta) {
		if (delta > 0) {
			rotations += delta;
		}
	}
	
	public int getRotations() {
		return rotations;
	}

	public String getType() {
		return "Bender";
	}
}

/* vim: set noet ts=4 sw=4: */
