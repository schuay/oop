public class DwarfTortoise extends Reptile {
	
	/*
	 * Creates a new DwarfTortoise with the passed name 
	 * (name != null)
	 */
	public DwarfTortoise(String name) {
		super(name);
	}

	/*
	 * Loads this specific Type of Animal into the passed transporter
	 * (t != null)
	 */
	public boolean loadObject(Transporter t) {
		return t.loadDwarfTortoise(this);
	}
}

/* vim: set noet ts=4 sw=4: */
