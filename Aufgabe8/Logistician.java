public class Logistician extends Worker {

	private final Quarry orig;
	private final VillageSquare dest;

	/* Creates a logistician with the specified name, duration, origin and
	 * destination storage areas.
	 * name, orig, dest != null
	 * duration > 0 */
	public Logistician(String name, int duration, Quarry orig, VillageSquare dest) {
		super(name, duration);
		this.orig = orig;
		this.dest = dest;
	}

	/* Take resources from orig, process them and place the results into
	 * dest as long as we haven't been asked to terminate and orig can still
	 * provide us with resources. Before exiting, unregister from dest.
	 * On interruption, all resources are either in dest or orig. */
	public void run() {
		final int d = getDuration();
		final int origCount = 2;
		final int destCount = 2;

		/* TODO: (this concerns all workers)
		 * if interrupted between getting resources and adding them
		 * to the next storage, what should be the result?
		 * a) output "carried" resources per worker?
		 * b) place them back in orig? (might not be possible because it
		 *    might've been filled by another worker in the meantime??)
		 * c) place them into dest? (might not be possible because dest is full?)
		 */

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
					orig.transferDone(origCount);
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
