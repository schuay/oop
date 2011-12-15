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
			while (true) {

				if (!orig.dec(origCount)) {
					return;
				}

				try {
					Thread.sleep(d);
				} catch (InterruptedException e) { /* TODO: handle Thread.interrupt() */ }

				dest.inc(destCount);
				incProduced(destCount);
			}
		} finally {
			dest.unregisterWorker(this);
		}
	}

}

/* vim: set noet ts=4 sw=4: */
