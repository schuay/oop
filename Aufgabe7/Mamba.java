public class Mamba extends Reptile {
	
	/*
	 * Creates a new Mamba with the passed name 
	 * (name != null)
	 */
	public Mamba(String name) {
		super(name);
	}

	/*
	 * Loads this specific Type of Animal into the passed transporter
	 * (t != null)
	 */
	public boolean loadObject(Transporter t) {
		return t.loadMamba(this);
	}	
}

/* vim: set noet ts=4 sw=4: */
