public class Mamba extends Reptile {
	
	public Mamba(String name) {
		super(name);
	}

	public boolean loadObject(Transporter t) {
		return t.loadMamba(this);
	}	
}

/* vim: set noet ts=4 sw=4: */
