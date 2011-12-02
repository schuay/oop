public class Welder implements Role {
	private final int temp;
	
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
