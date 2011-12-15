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

		try {
			while (!orig.workersDone()) {

				orig.dec(origCount);

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
