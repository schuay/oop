public class GiantTortoise extends Reptile {
	
	public GiantTortoise(String name) {
		super(name);
	}

	public boolean loadObject(Transporter t) {
		return t.loadGiantTortoise(this);
	}
}

/* vim: set noet ts=4 sw=4: */
