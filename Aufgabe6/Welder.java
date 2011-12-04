public class Welder implements Role {
	/* The temperature cannot be changed. */
	private final int temp;
	
	/* Create a Welder role with the given working temperature.
	 * The temperature cannot be changed afterwards. */
	public Welder(int temperature) {
		temp = temperature;
	}
	
	public String getRole() {
		return "Welder";
	}
	
	public int getWorkingTemp() {
		return temp;
	}
}

/* vim: set noet ts=4 sw=4: */
