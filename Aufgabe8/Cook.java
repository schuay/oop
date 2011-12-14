public class Cook extends Worker {

	private final ColdStorage orig;
	private final Table dest;

	public Cook(String name, int duration, ColdStorage orig, Table dest) {
		super(name, duration);
		this.orig = orig;
		this.dest = dest;
	}

	public void run() {
		// TODO Auto-generated method stub

	}

}

/* vim: set noet ts=4 sw=4: */
