public class Python extends Reptile {
	
	/*
	 * Creates a new Python with the passed name 
	 * (name != null)
	 */
	public Python(String name) {
		super(name);
	}

	/*
	 * Loads this specific Type of Animal into the passed transporter
	 * (t != null)
	 */
	public boolean loadObject(Transporter t) {
		return t.loadPython(this);
	}	
}

/* vim: set noet ts=4 sw=4: */
