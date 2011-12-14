public class Lumberjack extends Worker {

	private final Table orig;
	private final Quarry dest;

	public Lumberjack(String name, int duration, Table orig, Quarry dest) {
		super(name, duration);
		this.orig = orig;
		this.dest = dest;
	}

	public void run() {
		// TODO Auto-generated method stub

	}

}

/* vim: set noet ts=4 sw=4: */
