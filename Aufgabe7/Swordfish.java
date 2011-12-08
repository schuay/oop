public class Swordfish extends SeaAnimal {
	
	public Swordfish(String name) {
		super(name);
	}

	public boolean loadObject(Transporter t) {
		return t.loadSwordfish(this);
	}
}

/* vim: set noet ts=4 sw=4: */
