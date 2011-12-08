public class Dolphin extends SeaAnimal {
	
	public Dolphin(String name) {
		super(name);
	}

	public boolean loadObject(Transporter t) {
		return t.loadDolphin(this);
	}
	
}

/* vim: set noet ts=4 sw=4: */
