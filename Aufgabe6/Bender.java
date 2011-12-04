public class Bender extends Robot {
	/* The number of rotations can only increase.
	 * If the robot hasn't been used yet it is 0. */
	private int rotations;
	
	public Bender(int id) {
		super(id);
		rotations = 0;
	}
	
	/* Increase the rotations counter by delta. It is never decreased.
	 * (delta > 0) */
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
