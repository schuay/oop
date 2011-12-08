public abstract class LargeGame extends Animal implements TransportObject {
	
	public LargeGame(String name) {
		super(name);
	}
	
	/* Big game cannot be loaded at runtime and always returns false. */
	public final boolean loadObject(Transporter t) {
		return false;
	}
}

/* vim: set noet ts=4 sw=4: */
