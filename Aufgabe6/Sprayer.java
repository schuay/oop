public class Sprayer implements Role {
	private final double cap;

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
