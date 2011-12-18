import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* Storage stores an amount (0 <= amount <= capacity)
 * of some resource. */
public abstract class Storage {

	private final int capacity;
	private int count = 0;
	private int reserved = 0;
	private final Set<Worker> workers =
			Collections.synchronizedSet(new HashSet<Worker>());

	/* Constructs a new storage with specified capacity (> 0). */
	public Storage(int capacity) {
		if (capacity < 1) {
			throw new IllegalArgumentException();
		}

		this.capacity = capacity;
	}

	/* All workers (!= null) need to be registered before threads are run. */
	public void registerWorker(Worker worker) {
		workers.add(worker);
	}

	/* Workers (!= null) must unregister once they are done with their workload. */
	public void unregisterWorker(Worker worker) {
		Util.debug(String.format("%s: unregistering worker", getName()));
		workers.remove(worker);
		synchronized (this) { notifyAll(); }
	}

	/* Returns whether there are still active workers registered. */
	private boolean workersDone() {
		return workers.isEmpty();
	}

	/* Adds count (> 0) resources to the storage. Workers are blocked
	 * if storage cannot receive resources and woken up once it can. */
	public synchronized void inc(int count) throws InterruptedException {
		if (count < 1) {
			throw new IllegalArgumentException();
		}

		while (count + this.count + reserved > capacity) {
			Util.debug(String.format("%s full: blocking in inc()", getName()));
			wait();
		}

		this.count += count;
		notifyAll();
	}

	/* Reserves count (> 0) resources for a worker. Workers are blocked
	 * if storage does not contain sufficient resources until it does.
	 * Returns false if the request cannot be fulfilled, otherwise true. */
	public synchronized boolean dec(int count) throws InterruptedException {
		if (count < 1) {
			throw new IllegalArgumentException();
		}

		while (count > this.count) {
			if (workersDone()) {
				Util.debug(String.format(
					"%s empty and workers are done: returning from dec()", getName()));
				return false;
			}
			Util.debug(String.format("%s empty: blocking in dec()", getName()));
			wait();
		}

		this.count -= count;
		reserved += count;
		notifyAll();

		return true;
	}

	/* Completes a transaction by removing the requested count (> 0)
	 * from the requested set. */
	public synchronized void transferDone(int count) throws InterruptedException {
		if (count < 1) {
			throw new IllegalArgumentException();
		}

		reserved -= count;
		notifyAll();
	}

	/* Returns the specific name of the storage subtype. */
	protected abstract String getName();

	public String toString() {
		return String.format("%s resource count: %d", getName(), count + reserved);
	}
}

/* vim: set noet ts=4 sw=4: */
