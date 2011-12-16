public class Lumberjack extends Worker {

	private final Table orig;
	private final Quarry dest;

	public Lumberjack(String name, int duration, Table orig, Quarry dest) {
		super(name, duration);
		this.orig = orig;
		this.dest = dest;
	}

	public void run() {
		final int d = getDuration();
		final int origCount = 1;
		final int destCount = 1;

		try {
			boolean working = false;
			while (!getQuit()) {
				try {
					/* if working is set, the last loop iteration was interrupted
					 * during sleep() or inc(); in both cases, we didn't get a
					 * chance to inc(), so do it here without dec() */
					if (!working && !orig.dec(origCount)) {
						return;
					}
					working = true;

					Thread.sleep(d);

					dest.inc(destCount);
					incProduced(destCount);
					working = false;
				} catch (InterruptedException e) { }
			}
		} finally {
			Util.debug("TERMINATING: " + toString());
			dest.unregisterWorker(this);
		}
	}

}

/* vim: set noet ts=4 sw=4: */
