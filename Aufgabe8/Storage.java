public abstract class Storage {

	int count = 0;

	public synchronized void inc(int count) {
		if (count < 1) {
			return;
		}
		this.count += count;
	}

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
		return String.format("%s resource count: %d%n", getName(), count);
	}
}

/* vim: set noet ts=4 sw=4: */
