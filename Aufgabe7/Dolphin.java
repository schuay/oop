public class Dolphin extends SeaAnimal {
	
	/*
	 * Creates a new dolphin with the passed name 
	 * (name != null)
	 */
	public Dolphin(String name) {
		super(name);
	}

	/*
	 * Loads this specific Type of Animal into the passed transporter
	 * (t != null)
	 */
	public boolean loadObject(Transporter t) {
		return t.loadDolphin(this);
	}
	
}

/* vim: set noet ts=4 sw=4: */
