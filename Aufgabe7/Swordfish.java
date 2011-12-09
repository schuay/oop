public class Swordfish extends SeaAnimal {
	
	/*
	 * Creates a new Swordfish with the passed name 
	 * (name != null)
	 */
	public Swordfish(String name) {
		super(name);
	}

	/*
	 * Loads this specific Type of Animal into the passed transporter
	 * (t != null)
	 */
	public boolean loadObject(Transporter t) {
		return t.loadSwordfish(this);
	}
}

/* vim: set noet ts=4 sw=4: */
