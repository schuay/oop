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
		final int d = getDuration();

		try {
			while (getProduced() < count) {

				try {
					Thread.sleep(d);
				} catch (InterruptedException e) { /* TODO: handle Thread.interrupt() */ }

				dest.inc(1);
				incProduced(1);
			}
		} finally {
			Util.debug("TERMINATING: " + toString());
			dest.unregisterWorker(this);
		}
	}

}

/* vim: set noet ts=4 sw=4: */
