public abstract class Worker implements Runnable {

	private final int duration;
	private final String name;

	private int produced = 0;

	public Worker(String name, int duration) {

		if (duration < 1) {
			throw new IllegalArgumentException();
		}

		this.duration = duration;
		this.name = name;
	}

	protected int getDuration() {
		return duration;
	}

	protected void incProduced(int count) {
		produced += count;
	}

	public String toString() {
		return String.format("%s resources produced: %d", name, produced);
	}
}

/* vim: set noet ts=4 sw=4: */
