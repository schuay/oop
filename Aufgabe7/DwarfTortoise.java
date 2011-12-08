public class DwarfTortoise extends Reptile {
	
	public DwarfTortoise(String name) {
		super(name);
	}

	public boolean loadObject(Transporter t) {
		return t.loadDwarfTortoise(this);
	}
}

/* vim: set noet ts=4 sw=4: */
