/* A named worker which produces resources with a
 * specific work duration. */
public abstract class Worker implements Runnable {

	private final int duration;
	private final String name;

	private int produced = 0;

	private Boolean quit = false;

	/* Creates a worker with name (!= null) and a
	 * work duration (> 0). */
	public Worker(String name, int duration) {
		if (duration < 1 || name == null) {
			throw new IllegalArgumentException();
		}

		this.duration = duration;
		this.name = name;
	}

	/* Returns the work duration. */
	protected int getDuration() {
		return duration;
	}

	/* Increments the resource production count. */
	protected void incProduced(int count) {
		if (count < 1) {
			throw new IllegalArgumentException();
		}
		produced += count;
		Util.debug(toString());
	}

	/* Returns the resource production count. */
	protected int getProduced() {
		return produced;
	}

	/* Returns whether termination has been requested. */
	protected boolean getQuit() {
		synchronized (quit) {
			return quit;
		}
	}

	/* Request worker termination. */
	public void setQuit() {
		synchronized (quit) {
			quit = true;
		}
	}

	public String toString() {
		return String.format("%s resources produced: %d", name, produced);
	}
}

/* vim: set noet ts=4 sw=4: */
