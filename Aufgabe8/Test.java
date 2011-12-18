import java.util.HashMap;
import java.util.List;

public class Test {
	private static boolean result = true;
	private final static HashMap<Boolean, String> labels =
		new HashMap<Boolean, String>();
	private static int testCount = 0;
	private static int failedCount = 0;

	static {
		labels.put(true, "pass");
		labels.put(false, "FAIL");
	}	

	/**
	 * Main entry point of the testing suite.
	 * 
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) throws Exception {

		try {
			
			VerschenknixAG v;
			TestResult testresult;
			VerschenknixAG.Config config;
			
			config = new VerschenknixAG.Config();
			testresult = new TestResult(5, 25, 25, 24, 0, 0, 1, 24);
			v = new VerschenknixAG(config);
			test("Testing standard configuration, expecting one tree left in quarry",
					true, testSimulation(v, testresult));
			
			v = new VerschenknixAG(config);
			test("Testing if same result with same configuration",
					true, testSimulation(v, testresult));
				
			config = new VerschenknixAG.Config();
			config.hunter1Count = 3;
			testresult = new TestResult(6, 30, 30, 30, 0, 0, 0, 30);
			v = new VerschenknixAG(config);
			test("Testing configuration, all trees delivered",
					true, testSimulation(v, testresult));
			
			config = new VerschenknixAG.Config();
			config.hunter1Count = 1;
			config.hunter2Count = 1;
			v = new VerschenknixAG(config);
			testresult = new TestResult(2, 10, 10, 10, 0, 0, 0, 10);
			test("Testing with 1 hunt per hunter", true, testSimulation(v, testresult));
			
			config = new VerschenknixAG.Config();
			config.coldStorageCapacity = 1;
			testresult = new TestResult(5, 25, 25, 24, 0, 0, 1, 24);
			v = new VerschenknixAG(config);
			test("Testing configuration with cold storage capacity 1",
					true, testSimulation(v, testresult));
			
			config = new VerschenknixAG.Config();
			config.lumberjack1Duration = 5;
			config.lumberjack2Duration = 5;
			config.lumberjack3Duration = 5;
			config.lumberjack4Duration = 5;
			config.lumberjack5Duration = 5;
			testresult = new TestResult(5, 25, 25, 24, 0, 0, 1, 24);
			v = new VerschenknixAG(config);
			test("Testing configuration with all Lumberjacks needing the same time",
					true, testSimulation(v, testresult));
			
			config = new VerschenknixAG.Config();
			config.lumberjack1Duration = 1;
			config.lumberjack2Duration = 1;
			config.lumberjack3Duration = 1;
			config.lumberjack4Duration = 1;
			config.lumberjack5Duration = 1;
			config.cookDuration = 1;
			config.logistician1Duration = 1;
			config.logistician2Duration = 1;
			config.hunter1Duration = 1;
			config.hunter2Duration = 1;
			testresult = new TestResult(5, 25, 25, 24, 0, 0, 1, 24);
			v = new VerschenknixAG(config);
			test("Testing configuration with all workers needing the minimum time",
					true, testSimulation(v, testresult));
			
			config = new VerschenknixAG.Config();
			config.lumberjack1Duration = 1;
			config.lumberjack2Duration = 1;
			config.lumberjack3Duration = 1;
			config.lumberjack4Duration = 1;
			config.lumberjack5Duration = 1;
			config.cookDuration = 1;
			config.logistician1Duration = 1;
			config.logistician2Duration = 1;
			config.hunter1Duration = 50;
			config.hunter2Duration = 50;
			testresult = new TestResult(5, 25, 25, 24, 0, 0, 1, 24);
			v = new VerschenknixAG(config);
			test("Testing configuration with slow hunters and fast other workers",
					true, testSimulation(v, testresult));
			
			config = new VerschenknixAG.Config();
			config.lumberjack1Duration = 1;
			config.lumberjack2Duration = 1;
			config.lumberjack3Duration = 1;
			config.lumberjack4Duration = 1;
			config.lumberjack5Duration = 1;
			config.cookDuration = 50;
			config.logistician1Duration = 1;
			config.logistician2Duration = 1;
			config.hunter1Duration = 1;
			config.hunter2Duration = 1;
			testresult = new TestResult(5, 25, 25, 24, 0, 0, 1, 24);
			v = new VerschenknixAG(config);
			test("Testing configuration with slow cook", true, testSimulation(v, testresult));
			
			config = new VerschenknixAG.Config();
			config.coldStorageCapacity = 50;
			config.quarryCapacity = 50;
			config.tableCapacity = 50;
			testresult = new TestResult(5, 25, 25, 24, 0, 0, 1, 24);
			v = new VerschenknixAG(config);
			test("Testing configuration with large storage capacity",
					true, testSimulation(v, testresult));
			
			config = new VerschenknixAG.Config();
			config.logistician1Duration = 50;
			config.logistician2Duration = 50;
			config.quarryCapacity = 2;
			testresult = new TestResult(5, 25, 25, 24, 0, 0, 1, 24);
			v = new VerschenknixAG(config);
			test("Testing configuration with small quarry capacity and slow logisticians",
					true, testSimulation(v, testresult));
			
			config = new VerschenknixAG.Config();
			config.logistician1Duration = 1;
			config.logistician2Duration = 1;
			config.quarryCapacity = 2;
			testresult = new TestResult(5, 25, 25, 24, 0, 0, 1, 24);
			v = new VerschenknixAG(config);
			test("Testing configuration with small quarry capacity and fast logisticians",
					true, testSimulation(v, testresult));
			
			config = new VerschenknixAG.Config();
			config.cookDuration = 40;
			config.hunter1Duration = 1;
			config.hunter2Duration = 1;
			testresult = new TestResult(5, 25, 25, 24, 0, 0, 1, 24);
			v = new VerschenknixAG(config);
			test("Testing configuration with fast hunters and slow cook",
					true, testSimulation(v, testresult));
			
			config = new VerschenknixAG.Config();
			config.cookDuration = 40;
			config.hunter1Duration = 1;
			config.hunter2Duration = 1;
			config.coldStorageCapacity = 1;
			testresult = new TestResult(5, 25, 25, 24, 0, 0, 1, 24);
			v = new VerschenknixAG(config);
			test("Testing configuration with fast hunters and slow cook and small cold storage capacity",
					true, testSimulation(v, testresult));
			
			config = new VerschenknixAG.Config();
			config.cookDuration = 1;
			config.hunter1Duration = 40;
			config.hunter2Duration = 40;
			testresult = new TestResult(5, 25, 25, 24, 0, 0, 1, 24);
			v = new VerschenknixAG(config);
			test("Testing configuration with slow hunters and fast cook",
					true, testSimulation(v, testresult));
			
			config = new VerschenknixAG.Config();
			config.hunter1Count = 20;
			config.hunter2Count = 30;
			config.hunter1Duration = 3;
			config.hunter2Duration = 4;
			config.cookDuration = 4;
			config.lumberjack1Duration = 3;
			config.lumberjack2Duration = 4;
			config.lumberjack3Duration = 5;
			config.lumberjack4Duration = 6;
			config.lumberjack5Duration = 7;
			config.logistician1Duration = 3;
			config.logistician2Duration = 4;
			config.coldStorageCapacity = 1;
			config.tableCapacity = 5;
			config.quarryCapacity = 2;
			testresult = new TestResult(50, 250, 250, 250, 0, 0, 0, 250);
			v = new VerschenknixAG(config);
			test("Testing configuration with rather fast workers, low capacity and lots of work",
					true, testSimulation(v, testresult));
			
			config = new VerschenknixAG.Config();
			config.hunter1Count = 20;
			config.hunter2Count = 30;
			config.hunter1Duration = 3;
			config.hunter2Duration = 4;
			config.cookDuration = 4;
			config.lumberjack1Duration = 3;
			config.lumberjack2Duration = 4;
			config.lumberjack3Duration = 5;
			config.lumberjack4Duration = 6;
			config.lumberjack5Duration = 7;
			config.logistician1Duration = 3;
			config.logistician2Duration = 4;
			config.coldStorageCapacity = 30;
			config.tableCapacity = 30;
			config.quarryCapacity = 30;
			testresult = new TestResult(50, 250, 250, 250, 0, 0, 0, 250);
			v = new VerschenknixAG(config);
			test("Testing configuration with rather fast workers, high capacity and lots of work",
					true, testSimulation(v, testresult));
			
			config = new VerschenknixAG.Config();	
			config.hunter1Duration = 100;
			config.hunter2Duration = 100;
			v = new VerschenknixAG(config);
			test("Testing abort before hunters can finish", true, testAbort(v));
			
			config = new VerschenknixAG.Config();
			config.hunter1Count = 1;
			config.hunter2Count = 1;
			config.hunter1Duration = 1;
			config.hunter2Duration = 1;
			config.cookDuration = 100;
			v = new VerschenknixAG(config);
			test("Testing abort before cook can finish", true, testAbort(v));
			
			config = new VerschenknixAG.Config();
			config.hunter1Duration = 1;
			config.hunter2Duration = 1;
			config.hunter1Count = 1;
			config.hunter2Count = 1;
			config.cookDuration = 1;
			config.lumberjack1Duration = 100;
			config.lumberjack2Duration = 100;
			config.lumberjack3Duration = 100;
			config.lumberjack4Duration = 100;
			config.lumberjack5Duration = 100;
			config.coldStorageCapacity = 50;
			config.tableCapacity = 50;
			config.quarryCapacity = 50;
			v = new VerschenknixAG(config);
			test("Testing abort before lumberjacks can finish", true, testAbort(v));
			
			config = new VerschenknixAG.Config();
			config.hunter1Duration = 1;
			config.hunter2Duration = 1;
			config.cookDuration = 1;
			config.hunter1Count = 1;
			config.hunter2Count = 1;
			config.lumberjack1Duration = 1;
			config.lumberjack2Duration = 1;
			config.lumberjack3Duration = 1;
			config.lumberjack4Duration = 1;
			config.lumberjack5Duration = 1;
			config.coldStorageCapacity = 50;
			config.tableCapacity = 50;
			config.quarryCapacity = 50;
			config.logistician1Duration = 100;
			config.logistician2Duration = 100;
			v = new VerschenknixAG(config);
			test("Testing abort before logisticians can finish", true, testAbort(v));
			
			printSummary();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.printf(
				"%n%nUnexpected exception thrown after %d tests; testing aborted!%n",
				testCount);
		}
	}
	
	public static boolean testSimulation(VerschenknixAG v, TestResult tr) {		
		v.simulate();
		TestResult res = summary2TestResult(v.getSummary());
		return res.equals(tr);
	}
	
	public static boolean testAbort(VerschenknixAG v) {
		v.simulateAbort(50);
		TestResult res = summary2TestResult(v.getSummary());
		int sum = res.coldstoragecount + (res.quarrycount + res.villagesquarecount + res.tablecount)/5;
		return (sum == res.huntercombined);
	}
	
	/**
	 * Prints a single test result.
	 * 
	 * @param test
	 *            Test description
	 * @param expected
	 *            Expected test outcome
	 * @param got
	 *            Actual test outcome
	 */
	private static void test(String test, Object expected, Object got) {
		boolean succeeded;

		if (expected == null) {
			succeeded = (expected == got);
		} else {
			succeeded = expected.equals(got);
		}

		if (!succeeded) {
			failedCount++;
		}
		
		System.out.printf("%04d: %s%n%s: expected: %s, got: %s%n%n",
				testCount++, test, labels.get(succeeded), abbreviate(expected),
				abbreviate(got));
		result = result && succeeded;
	}

	private static void printSummary() {
		System.out.printf("%nFinal result: %s (%d passed, %d failed)%n%n",
				labels.get(result), testCount - failedCount, failedCount);
	}

	private static String abbreviate(Object o) {

		if (o == null) {
			return null;
		}

		int cutoff = 50;
		String str = o.toString();

		if (!str.contains("\n") && str.length() < cutoff) {
			return str;
		}

		if (str.contains("\n")) {
			cutoff = Math.min(cutoff, str.indexOf('\n'));
		}
		return str.substring(0, cutoff) + " [...]";
	}
	
	public static TestResult summary2TestResult(List<String> summary) {
		String[] lines = new String[summary.size()];
		int i=0;
		for (String s : summary) {
			lines[i] = s.substring(s.lastIndexOf(":")+1).trim();
			i++;
		}
		int hunter1produced = Integer.parseInt(lines[0]);
		int hunter2produced = Integer.parseInt(lines[1]);
		int cookproduced = Integer.parseInt(lines[2]);
		int lumberjack1produced = Integer.parseInt(lines[3]);
		int lumberjack2produced = Integer.parseInt(lines[4]);
		int lumberjack3produced = Integer.parseInt(lines[5]);
		int lumberjack4produced = Integer.parseInt(lines[6]);
		int lumberjack5produced = Integer.parseInt(lines[7]);
		int logistician1produced = Integer.parseInt(lines[8]);
		int logistician2produced = Integer.parseInt(lines[9]);
		int coldstoragecount = Integer.parseInt(lines[10]);
		int tablecount = Integer.parseInt(lines[11]);
		int quarrycount = Integer.parseInt(lines[12]);
		int villagesquarecount = Integer.parseInt(lines[13]);
		
		return new TestResult(hunter1produced + hunter2produced, cookproduced,
				lumberjack1produced + lumberjack2produced + lumberjack3produced +
				lumberjack4produced + lumberjack5produced, logistician1produced +
				logistician2produced, coldstoragecount, tablecount, quarrycount,
				villagesquarecount);
	}
	
	private static class TestResult {
		private int huntercombined;
		private int cookproduced;
		private int lumberjackcombined;
		private int logisticiancombined;
		private int coldstoragecount;
		private int tablecount;
		private int quarrycount;
		private int villagesquarecount;
		
		private TestResult(int huntercombined, int cookproduced, int lumberjackcombined,
				int logisticiancombined, int coldstoragecount, int tablecount,
				int quarrycount, int villagesquarecount) {
			this.huntercombined = huntercombined;
			this.cookproduced = cookproduced; 
			this.lumberjackcombined = lumberjackcombined;
			this.logisticiancombined = logisticiancombined;
			this.coldstoragecount = coldstoragecount; 
			this.tablecount = tablecount; 
			this.quarrycount = quarrycount; 
			this.villagesquarecount = villagesquarecount; 
		}
		
		public boolean equals(TestResult that) {
			return (this.huntercombined == that.huntercombined &&
					this.coldstoragecount == that.coldstoragecount &&
					this.cookproduced == that.cookproduced &&
					this.lumberjackcombined == that.lumberjackcombined &&
					this.huntercombined == that.huntercombined &&
					this.logisticiancombined == that.logisticiancombined &&
					this.tablecount == that.tablecount &&
					this.quarrycount == that.quarrycount &&
					this.villagesquarecount == that.villagesquarecount);
		}
	}
}
/* vim: set noet ts=4 sw=4: */
