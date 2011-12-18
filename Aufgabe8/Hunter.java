public class Hunter extends Worker {

	private final ColdStorage dest;
	private final int count;

	/* Creates a hunter with the specified name, duration, and
	 * destination storage area.
	 * name, dest != null
	 * duration, count > 0 */
	public Hunter(String name, int count, int duration, ColdStorage dest) {
		super(name, duration);
		if (count < 1) {
			throw new IllegalArgumentException();
		}
		this.dest = dest;
		this.count = count;
	}

	/* Hunt boars and place them into
	 * dest as long as we haven't been asked to terminate and we haven't
	 * produced the requested amount. Before exiting, unregister from dest. */
	public void run() {
		final int d = getDuration();

		try {
			while (getProduced() < count && !getQuit()) {

				try {
					Thread.sleep(d);

					dest.inc(1);
					incProduced(1);
				} catch (InterruptedException e) { }
			}
		} finally {
			Util.debug("TERMINATING: " + toString());
			dest.unregisterWorker(this);
		}
	}

}

/* vim: set noet ts=4 sw=4: */
