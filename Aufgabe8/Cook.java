public class Cook extends Worker {

	private final ColdStorage orig;
	private final Table dest;

	public Cook(String name, int duration, ColdStorage orig, Table dest) {
		super(name, duration);
		this.orig = orig;
		this.dest = dest;
	}

	public void run() {
		final int d = getDuration();
		final int origCount = 1;
		final int destCount = 5;

		try {
			while (!getQuit()) {
				try {
					if (!orig.dec(origCount)) {
						return;
					}
					Thread.sleep(d);

					dest.inc(destCount);
					incProduced(destCount);
				} catch (InterruptedException e) { }
			}
		} finally {
			Util.debug("TERMINATING: " + toString());
			dest.unregisterWorker(this);
		}
	}

}

/* vim: set noet ts=4 sw=4: */
