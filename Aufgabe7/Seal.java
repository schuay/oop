public class Seal extends SeaAnimal {
	
	public Seal(String name) {
		super(name);
	}

	public boolean loadObject(Transporter t) {
		return t.loadSeal(this);
	}
}

/* vim: set noet ts=4 sw=4: */
