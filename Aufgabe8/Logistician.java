public class Logistician extends Worker {

	private final Quarry orig;
	private final VillageSquare dest;

	public Logistician(String name, int duration, Quarry orig, VillageSquare dest) {
		super(name, duration);
		this.orig = orig;
		this.dest = dest;
	}

	public void run() {
		final int d = getDuration();
		final int origCount = 2;
		final int destCount = 2;

		/* TODO: handle situation where orig workers are done,
		 * but only 1 resource is available. */

		try {
			while (!orig.workersDone()) {

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
