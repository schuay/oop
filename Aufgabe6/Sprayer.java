public class Sprayer implements Role {
	/* The capacity cannot be changed and must not be negative. */
	private final double cap;

	/* Create a Sprayer role with the given capacity.
	 * The capacity cannot be changed afterwards and must not be negative. */
	public Sprayer(double capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException();
		}
		cap = capacity;
	}
	
	public String getRole() {
		return "Sprayer";
	}
	
	public double getCapacity() {
		return cap;
	}
}

/* vim: set noet ts=4 sw=4: */
