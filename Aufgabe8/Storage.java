import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Storage {

	private final int capacity;
	private int count = 0;
	private final Set<Worker> workers =
			Collections.synchronizedSet(new HashSet<Worker>());

	public Storage(int capacity) {

		if (capacity < 1) {
			throw new IllegalArgumentException();
		}

		this.capacity = capacity;
	}

	/* All workers need to be registered before threads are run. */
	public void registerWorker(Worker worker) {
		workers.add(worker);
	}

	/* Workers must unregister once they are done with their workload. */
	public void unregisterWorker(Worker worker) {
		Util.debug(String.format("%s: unregistering worker", getName()));
		workers.remove(worker);
		synchronized (this) { notifyAll(); }
	}

	private boolean workersDone() {
		return workers.isEmpty();
	}

	/* Returns false if count is invalid, otherwise true. */
	public synchronized boolean inc(int count) {
		if (count < 1) {
			return false;
		}

		while (count + this.count > capacity) {
			try {
				Util.debug(String.format("%s full: blocking in inc()", getName()));
				wait();
			} catch (InterruptedException e) { /* TODO: handle Thread.interrupt() */ }
		}

		this.count += count;
		notifyAll();

		return true;
	}

	/* Returns false if count is invalid or request cannot be fulfilled,
	 * otherwise true. */
	public synchronized boolean dec(int count) {
		if (count < 1) {
			return false;
		}

		while (count > this.count) {
			try {
				if (workersDone()) {
					Util.debug(String.format("%s empty and workers are done: returning from dec()", getName()));
					return false;
				}
				Util.debug(String.format("%s empty: blocking in dec()", getName()));
				wait();
			} catch (InterruptedException e) { /* TODO: handle Thread.interrupt() */ }
		}

		this.count -= count;
		notifyAll();

		return true;
	}

	protected abstract String getName();

	public String toString() {
		return String.format("%s resource count: %d", getName(), count);
	}
}

/* vim: set noet ts=4 sw=4: */
