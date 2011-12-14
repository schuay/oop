public abstract class Storage {

	private final int capacity;
	private int count = 0;

	public Storage(int capacity) {

		if (capacity < 1) {
			throw new IllegalArgumentException();
		}

		this.capacity = capacity;
	}

	/* Returns false if count is invalid or capacity would be exceeded,
	 * otherwise true. */
	public synchronized boolean inc(int count) {
		if (count < 1) {
			return false;
		} else if (count + this.count > capacity) {
			return false;
		}
		this.count += count;
		return true;
	}

	/* Returns false if count is invalid or request cannot be fulfilled,
	 * otherwise true. */
	public synchronized boolean dec(int count) {
		if (count < 1) {
			return false;
		} else if (count > this.count) {
			return false;
		}
		this.count -= count;
		return true;
	}

	protected abstract String getName();

	public String toString() {
		return String.format("%s resource count: %d", getName(), count);
	}
}

/* vim: set noet ts=4 sw=4: */
