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
		workers.remove(worker);
	}

	private boolean workersDone() {
		return workers.isEmpty();
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
