public class Hunter extends Worker {

	private final ColdStorage dest;
	private final int count;

	public Hunter(String name, int count, int duration, ColdStorage dest) {
		super(name, duration);
		if (count < 1) {
			throw new IllegalArgumentException();
		}
		this.dest = dest;
		this.count = count;
	}

	public void run() {
		// TODO Auto-generated method stub

	}

}

/* vim: set noet ts=4 sw=4: */
