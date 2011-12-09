public class Seal extends SeaAnimal {
	
	/*
	 * Creates a new Seal with the passed name 
	 * (name != null)
	 */
	public Seal(String name) {
		super(name);
	}

	/*
	 * Loads this specific Type of Animal into the passed transporter
	 * (t != null)
	 */
	public boolean loadObject(Transporter t) {
		return t.loadSeal(this);
	}
}

/* vim: set noet ts=4 sw=4: */
