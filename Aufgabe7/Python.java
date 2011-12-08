public class Python extends Reptile {
	
	public Python(String name) {
		super(name);
	}

	public boolean loadObject(Transporter t) {
		return t.loadPython(this);
	}	
}

/* vim: set noet ts=4 sw=4: */
