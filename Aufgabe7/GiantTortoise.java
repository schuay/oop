public class GiantTortoise extends Reptile {
	
	/*
	 * Creates a new GiantTortoise with the passed name 
	 * (name != null)
	 */
	public GiantTortoise(String name) {
		super(name);
	}

	/*
	 * Loads this specific Type of Animal into the passed transporter
	 * (t != null)
	 */
	public boolean loadObject(Transporter t) {
		return t.loadGiantTortoise(this);
	}
}

/* vim: set noet ts=4 sw=4: */
