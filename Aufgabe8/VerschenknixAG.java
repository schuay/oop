import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class VerschenknixAG {

	private final Set<Worker> workers = new LinkedHashSet<Worker>();
	private final Set<Storage> storage = new LinkedHashSet<Storage>();
	private final Set<Thread> threads = new LinkedHashSet<Thread>();

	public static class Config {
		public int hunter1Count = 2;
		public int hunter1Duration = 5;
		public int hunter2Count = 3;
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

		Hunter w0 = new Hunter("Hunter 1", config.hunter1Count, config.hunter1Duration, s1);
		Hunter w1 = new Hunter("Hunter 2", config.hunter2Count, config.hunter2Duration, s1);
		Cook w2 = new Cook("Cook", config.cookDuration, s1, s2);
		Lumberjack w3 = new Lumberjack("Lumberjack 1", config.lumberjack1Duration, s2, s3);
		Lumberjack w4 = new Lumberjack("Lumberjack 2", config.lumberjack2Duration, s2, s3);
		Lumberjack w5 = new Lumberjack("Lumberjack 3", config.lumberjack3Duration, s2, s3);
		Lumberjack w6 = new Lumberjack("Lumberjack 4", config.lumberjack4Duration, s2, s3);
		Lumberjack w7 = new Lumberjack("Lumberjack 5", config.lumberjack5Duration, s2, s3);
		Logistician w8 = new Logistician("Logistician 1", config.logistician1Duration, s3, s4);
		Logistician w9 = new Logistician("Logistician 2", config.logistician2Duration, s3, s4);

		s1.registerWorker(w0);
		s1.registerWorker(w1);
		s2.registerWorker(w2);
		s3.registerWorker(w3);
		s3.registerWorker(w4);
		s3.registerWorker(w5);
		s3.registerWorker(w6);
		s3.registerWorker(w7);
		s4.registerWorker(w8);
		s4.registerWorker(w9);

		storage.add(s1);
		storage.add(s2);
		storage.add(s3);
		storage.add(s4);

		workers.add(w0);
		workers.add(w1);
		workers.add(w2);
		workers.add(w3);
		workers.add(w4);
		workers.add(w5);
		workers.add(w6);
		workers.add(w7);
		workers.add(w8);
		workers.add(w9);
	}

	private void simulateInternal() {
		threads.clear();
		for (Worker w : workers) {
			Thread thread = new Thread(w);
			threads.add(thread);
			thread.start();
		}		
	}

	private void joinAll() {
		for (Thread t : threads) {
			while (true) {
				try {
					t.join();
					break;
				} catch (InterruptedException e) { }
			}
		}
	}

	public void simulate() {
		simulateInternal();
		joinAll();
		Util.debug("All done.");
	}

	public void simulateAbort(int duration) {
		simulateInternal();

		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (Worker w : workers) {
			w.setQuit();
		}

		for (Thread t : threads) {
			t.interrupt();
		}
		joinAll();

		Util.debug("All done.");
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
