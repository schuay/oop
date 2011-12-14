import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class VerschenknixAG {

	private final Set<Worker> workers = new LinkedHashSet<Worker>();
	private final Set<Storage> storage = new LinkedHashSet<Storage>();
	private final Set<Thread> threads = new LinkedHashSet<Thread>();

	public static class Config {
		public int hunter1Duration = 5;
		public int hunter2Duration = 7;
		public int cookDuration = 15;
		public int lumberjack1Duration = 2;
		public int lumberjack2Duration = 40;
		public int lumberjack3Duration = 7;
		public int lumberjack4Duration = 10;
		public int lumberjack5Duration = 25;
		public int logistician1Duration = 9;
		public int logistician2Duration = 15;

		public int coldStorageCapacity = 5;
		public int tableCapacity = 5;
		public int quarryCapacity = 5;
		public int villageSquareCapacity = Integer.MAX_VALUE;
	}

	public VerschenknixAG(VerschenknixAG.Config config) {

		ColdStorage s1 = new ColdStorage(config.coldStorageCapacity);
		Table s2 = new Table(config.tableCapacity);
		Quarry s3 = new Quarry(config.quarryCapacity);
		VillageSquare s4 = new VillageSquare(config.villageSquareCapacity);

		storage.add(s1);
		storage.add(s2);
		storage.add(s3);
		storage.add(s4);

		workers.add(new Hunter("Hunter 1", config.hunter1Duration, s1));
		workers.add(new Hunter("Hunter 2", config.hunter2Duration, s1));
		workers.add(new Cook("Cook", config.cookDuration, s1, s2));
		workers.add(new Lumberjack("Lumberjack 1", config.lumberjack1Duration, s2, s3));
		workers.add(new Lumberjack("Lumberjack 2", config.lumberjack2Duration, s2, s3));
		workers.add(new Lumberjack("Lumberjack 3", config.lumberjack3Duration, s2, s3));
		workers.add(new Lumberjack("Lumberjack 4", config.lumberjack4Duration, s2, s3));
		workers.add(new Lumberjack("Lumberjack 5", config.lumberjack5Duration, s2, s3));
		workers.add(new Logistician("Logistician 1", config.logistician1Duration, s3, s4));
		workers.add(new Logistician("Logistician 2", config.logistician2Duration, s3, s4));
	}

	private void simulateInternal() {
		threads.clear();
		for (Worker w : workers) {
			Thread thread = new Thread(w);
			threads.add(thread);
			thread.start();
		}		
	}

	public void simulate() {
		simulateInternal();

		for (Thread t : threads) {
			while (true) {
				try {
					t.join();
					break;
				} catch (InterruptedException e) { }
			}
		}

	}

	public void simulateAbort(int duration) {
		simulateInternal();

		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (Thread t : threads) {
			t.interrupt();
		}
	}

	public List<String> getSummary() {
		List<String> l = new ArrayList<String>();

		for (Worker w : workers) {
			l.add(w.toString());
		}
		for (Storage s : storage) {
			l.add(s.toString());
		}

		return l;
	}

	public void printSummary() {
		for (String s : getSummary()) {
			System.out.println(s.toString());
		}
	}
}

/* vim: set noet ts=4 sw=4: */
