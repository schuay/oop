import java.util.HashMap;

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
	
	private static class ExpectedFactoryResults {
		public double avgHoursUsed;
		public double avgHoursUsedBender;
		public double avgHoursUsedCrawler;
		public double avgHoursUsedWelder;
		public double avgHoursUsedSprayer;
		public double avgRotations;
		public double avgRotationsWelder;
		public double avgRotationsSprayer;
		public double avgTravelled;
		public double avgTravelledWelder;
		public double avgTravelledSprayer;
		public int minWorkingTemp;
		public int minWorkingTempBender;
		public int minWorkingTempCrawler;
		public int maxWorkingTemp;
		public int maxWorkingTempBender;
		public int maxWorkingTempCrawler;
	}
	
	private static void testBender(Bender bender) {
		int rotations = bender.getRotations();
		int delta = 3;
		
		bender.incRotations(3);
		test("bender.incRotations(3) = rot + 3", rotations + delta, bender.getRotations());

		bender.incRotations(0);
		test("bender.incRotations(0) = rot", rotations + delta, bender.getRotations());

		bender.incRotations(-1);
		test("bender.incRotations(-1) = rot", rotations + delta, bender.getRotations());
	}
	
	private static void testCrawler(Crawler crawler) {
		double rotations = crawler.getTravelledDistance();
		double delta = 3;
		
		crawler.incTravelledDistance(delta);
		test("crawler.incTravelledDistance(3)", rotations + delta, crawler.getTravelledDistance());

		crawler.incTravelledDistance(0);
		test("crawler.incTravelledDistance(0)", rotations + delta, crawler.getTravelledDistance());

		crawler.incTravelledDistance(-1);
		test("crawler.incTravelledDistance(-1)", rotations + delta, crawler.getTravelledDistance());
	}
	
	private static void testDictionary() {
		final Robot r1 = new Bender(1);
		final Robot r2 = new Bender(2);
		final Robot r3 = new Bender(3);
		final Robot r4 = new Bender(4);
		
		Dictionary dict = new Dictionary();

		boolean result = false;
		try {
			dict.put(null, null);
		} catch (IllegalArgumentException e) {
			result = true;
		}
		test("dict.put() with null arg", true, result);

		result = false;
		try {
			dict.get(null);
		} catch (IllegalArgumentException e) {
			result = true;
		}
		test("dict.get() with null arg", true, result);

		result = false;
		try {
			dict.remove(null);
		} catch (IllegalArgumentException e) {
			result = true;
		}
		test("dict.remove() with null arg", true, result);

		result = false;
		try {
			dict.containsKey(null);
		} catch (IllegalArgumentException e) {
			result = true;
		}
		test("dict.containsKey() with null arg", true, result);
		
		test("empty dict.size()", 0, dict.size());
		test("empty dict.containsKey()", false, dict.containsKey(r1));
		test("empty dict.get()", null, dict.get(r1));
		
		dict.remove(r1);
		test("dict.remove() with object not in dict", "void", "void");
		
		dict.clear();
		test("dict.clear() with object not in dict", "void", "void");
		
		dict.put(1, r1);
		dict.put(2, r2);
		dict.put(3, r3);
		dict.put(4, r4);
		
		Robot r = (Robot)dict.get(1);
		test("dict.get() with key in dict", r1, r);
		
		dict.remove(1);
		
		r = (Robot)dict.get(1);
		test("dict.get() with key not in dict after remove()", null, r);
		
		Iter it = dict.values();
		r = (Robot)it.next();
		test("traversing dict.values(), item 1", r2, r);
		r = (Robot)it.next();
		test("traversing dict.values(), item 2", r3, r);
		r = (Robot)it.next();
		test("traversing dict.values(), item 3", r4, r);
		r = (Robot)it.next();
		test("traversing dict.values(), past end of list", null, r);
		
		dict.put(2, r1);
		test("dict.put() with key already in dict", r1, dict.get(2));

		test("dict.size()", 3, dict.size());
		
		dict.clear();
		test("dict.size() after dict.clear()", 0, dict.size());		
	}

	private static void testRobot(Robot robot) {
		int hrs = robot.getHoursUsed();

		int delta = 3;
		robot.incHoursUsed(delta);
		test("Incrementing hours used by 3", hrs + delta, robot.getHoursUsed());

		hrs = robot.getHoursUsed();
		robot.incHoursUsed(0);
		test("Incrementing hours used by 0", hrs, robot.getHoursUsed());

		hrs = robot.getHoursUsed();
		robot.incHoursUsed(-1);
		test("Incrementing hours used by -1", hrs, robot.getHoursUsed());

		boolean result = false;
		try {
			robot.setRole(null);
		} catch (IllegalArgumentException e) {
			result = true;
		}
		test("Call robot.setRole(null)", true, result);

		Sprayer s = new Sprayer(15.0);
		robot.setRole(s);
		Sprayer t = (Sprayer)robot.getRole();
		test("robot.getRole() after robot.setRole()", s, t);
		
		try {
			robot.setRole(null);
		} catch (IllegalArgumentException e) { }
		t = (Sprayer)robot.getRole();
		test("robot.setRole(null) doesn't change existing role", s, t);
	}
	
	private static void testRoles() {

		boolean result = false;
		try {
			new Sprayer(-1);
		} catch (IllegalArgumentException e) {
			result = true;
		}
		test("Sprayer ctor with negative arg", true, result);

		Sprayer s = new Sprayer(15.0);
		test("Sprayer.getCapacity()", 15.0, s.getCapacity());
		
		
		Welder w = new Welder(-10);
		test("Welder.getWorkingTemp()", -10, w.getWorkingTemp());
		
		Role r = s;
		test("Sprayer.getRole()", "Sprayer", r.getRole());
		r = w;
		test("Welder.getRole()", "Welder", r.getRole());	
	}
	
	private static void testFactory(Factory factory, ExpectedFactoryResults results) {

		test("getAvgHoursUsed()", results.avgHoursUsed, factory.getAvgHoursUsed());
		
		Dictionary d = factory.getAvgHoursUsedByType();
		test("getAvgHoursUsedByType()", results.avgHoursUsedBender, d.get("Bender"));
		test("getAvgHoursUsedByType()", results.avgHoursUsedCrawler, d.get("Crawler"));
		
		d = factory.getAvgHoursUsedByRole();
		test("getAvgHoursUsedByRole()", results.avgHoursUsedWelder, d.get("Welder"));
		test("getAvgHoursUsedByRole()", results.avgHoursUsedSprayer, d.get("Sprayer"));
		
		test("getAvgRotations()", results.avgRotations, factory.getAvgRotations());
		
		d = factory.getAvgRotationsByRole();
		test("getAvgRotationsByRole()", results.avgRotationsWelder, d.get("Welder"));
		test("getAvgRotationsByRole()", results.avgRotationsSprayer, d.get("Sprayer"));
		
		test("getAvgTravelled()", results.avgTravelled, factory.getAvgTravelled());
		
		d = factory.getAvgTravelledByRole();
		test("getAvgTravelledByRole()", results.avgTravelledWelder, d.get("Welder"));
		test("getAvgTravelledByRole()", results.avgTravelledSprayer, d.get("Sprayer"));
		
		test("getMinWorkingTemp()", results.minWorkingTemp, factory.getMinWorkingTemp());
		
		d = factory.getMinWorkingTempByType();
		test("getMinWorkingTempByType()", results.minWorkingTempBender, d.get("Bender"));
		test("getMinWorkingTempByType()", results.minWorkingTempCrawler, d.get("Crawler"));
		
		test("getMaxWorkingTemp()", results.maxWorkingTemp, factory.getMaxWorkingTemp());

		d = factory.getMaxWorkingTempByType();
		test("getMaxWorkingTempByType()", results.maxWorkingTempBender, d.get("Bender"));
		test("getMaxWorkingTempByType()", results.maxWorkingTempCrawler, d.get("Crawler"));
	}
	
	private static void testFactory() {

		boolean result = false;
		try {
			new Factory(null);
		} catch (IllegalArgumentException e) {
			result = true;
		}
		test("Factory ctor with null arg", true, result);
		
		Factory f = new Factory("a");

		result = false;
		try {
			f.delRobot(1);
		} catch (IllegalArgumentException e) {
			result = true;
		}
		test("Factory.delRobot() with nonexistant robot", true, result);

		result = false;
		try {
			f.robotIncHoursUsed(1, 1);
		} catch (IllegalArgumentException e) {
			result = true;
		}
		test("Factory.robotIncHoursUsed() with nonexistant robot", true, result);

		result = false;
		try {
			f.robotGetHoursUsed(1);
		} catch (IllegalArgumentException e) {
			result = true;
		}
		test("Factory.robotGetHoursUsed() with nonexistant robot", true, result);

		result = false;
		try {
			f.benderIncRotations(1, 1);
		} catch (IllegalArgumentException e) {
			result = true;
		}
		test("Factory.benderIncRotations() with nonexistant robot", true, result);

		result = false;
		try {
			f.benderGetRotations(1);
		} catch (IllegalArgumentException e) {
			result = true;
		}
		test("Factory.benderGetRotations() with nonexistant robot", true, result);

		result = false;
		try {
			f.crawlerIncTravelled(1, 1);
		} catch (IllegalArgumentException e) {
			result = true;
		}
		test("Factory.crawlerIncTravelled() with nonexistant robot", true, result);

		result = false;
		try {
			f.crawlerGetTravelled(1);
		} catch (IllegalArgumentException e) {
			result = true;
		}
		test("Factory.crawlerGetTravelled() with nonexistant robot", true, result);

		result = false;
		try {
			f.robotSetRole(1, new Welder(1));
		} catch (IllegalArgumentException e) {
			result = true;
		}
		test("Factory.robotSetRole() with nonexistant robot", true, result);
		
		f.addRobot(new Bender(1));
		f.addRobot(new Crawler(2));
		
		result = false;
		try {
			f.benderIncRotations(2, 1);
		} catch (IllegalArgumentException e) {
			result = true;
		}
		test("Factory.benderIncRotations() with wrong type", true, result);

		result = false;
		try {
			f.benderGetRotations(2);
		} catch (IllegalArgumentException e) {
			result = true;
		}
		test("Factory.benderGetRotations() with wrong type", true, result);

		result = false;
		try {
			f.crawlerIncTravelled(1, 1);
		} catch (IllegalArgumentException e) {
			result = true;
		}
		test("Factory.crawlerIncTravelled() with wrong type", true, result);

		result = false;
		try {
			f.crawlerGetTravelled(1);
		} catch (IllegalArgumentException e) {
			result = true;
		}
		test("Factory.crawlerGetTravelled() with wrong type", true, result);
		
		f.robotIncHoursUsed(1, 5);
		test("Factory.robotIncHoursUsed()", 5, f.robotGetHoursUsed(1));
		test("Factory.robotGetHoursUsed()", 0, f.robotGetHoursUsed(2));

		f.robotIncHoursUsed(2, 3);
		test("Factory.robotIncHoursUsed()", 3, f.robotGetHoursUsed(2));
		test("Factory.robotGetHoursUsed()", 5, f.robotGetHoursUsed(1));
		
		f.benderIncRotations(1, 5);
		test("Factory.benderIncRotations()", 5, f.benderGetRotations(1));
		
		f.crawlerIncTravelled(2, 5.5);
		test("Factory.crawlerIncTravelled()", 5.5, f.crawlerGetTravelled(2));
		
		result = false;
		try {
			f.robotSetRole(1, null);
		} catch (IllegalArgumentException e) {
			result = true;
		}
		test("Factory.robotSetRole() with null arg", true, result);
		
		Welder w = new Welder(-10);
		Sprayer s = new Sprayer(15.0);
		f.robotSetRole(1, w);
		f.robotSetRole(2, s);
		
		f.delRobot(1);
	}
	
	private static void testFactoryDictionary() {

		Dictionary dict = new Dictionary();

		Factory fa = new Factory("a");
		Factory fb = new Factory("b");
		
		Crawler c1 = new Crawler(1); /* 0 hrs 5 dist 10 temp welder A*/
		Crawler c2 = new Crawler(2); /* 0 hrs 10 dist 10 cap sprayer B */
		Crawler c3 = new Crawler(3); /* 5 hrs 15 dist 20 cap sprayer A */
		Crawler c4 = new Crawler(4); /* 10 hrs 0 dist B */
		
		c1.incTravelledDistance(5);
		c2.incTravelledDistance(10);
		c3.incTravelledDistance(15);
				
		Bender b1 = new Bender(1); /* 0 hrs 5 rot 10 temp welder B */
		Bender b2 = new Bender(2); /* 0 hrs 10 rot 10 cap sprayer A */
		Bender b3 = new Bender(3); /* 15 hrs 15 rot 20 cap sprayer B */
		Bender b4 = new Bender(4); /* 20 hrs 0 rot A */
		
		b1.incRotations(5);
		b2.incRotations(10);
		b3.incRotations(15);
		
		c3.incHoursUsed(5);
		c4.incHoursUsed(10);
		b3.incHoursUsed(15);
		b4.incHoursUsed(20);
		
		Sprayer s1 = new Sprayer(10);
		Sprayer s2 = new Sprayer(20);
		
		Welder w1 = new Welder(10);
		
		c2.setRole(s1);
		b2.setRole(s1);
		c3.setRole(s2);
		b3.setRole(s2);
		c1.setRole(w1);
		b1.setRole(w1);

		fa.addRobot(c1);
		fa.addRobot(c3);
		fa.addRobot(b2);
		fa.addRobot(b4);

		fb.addRobot(c2);
		fb.addRobot(c4);
		fb.addRobot(b1);
		fb.addRobot(b3);
		
		dict.put("a", fa);
		dict.put("b", fb);
		
		ExpectedFactoryResults results = new ExpectedFactoryResults();	
		results.avgHoursUsed = 6.25;
		results.avgHoursUsedBender = 10;
		results.avgHoursUsedCrawler = 2.5;
		results.avgHoursUsedWelder = 0;
		results.avgHoursUsedSprayer = 2.5;
		results.avgRotations = 5;
		results.avgRotationsWelder = 0;
		results.avgRotationsSprayer = 10;
		results.avgTravelled = 10;
		results.avgTravelledWelder = 5;
		results.avgTravelledSprayer = 15;
		results.minWorkingTemp = 10;
		results.minWorkingTempBender = 0;
		results.minWorkingTempCrawler = 10;
		results.maxWorkingTemp = 10;
		results.maxWorkingTempBender = 0;
		results.maxWorkingTempCrawler = 10;	
		
		testFactory((Factory)dict.get("a"), results);
		
		results = new ExpectedFactoryResults();	
		results.avgHoursUsed = 6.25;
		results.avgHoursUsedBender = 7.5;
		results.avgHoursUsedCrawler = 5;
		results.avgHoursUsedWelder = 0;
		results.avgHoursUsedSprayer = 7.5;
		results.avgRotations = 10;
		results.avgRotationsWelder = 5;
		results.avgRotationsSprayer = 15;
		results.avgTravelled = 5;
		results.avgTravelledWelder = 0;
		results.avgTravelledSprayer = 10;
		results.minWorkingTemp = 10;
		results.minWorkingTempBender = 10;
		results.minWorkingTempCrawler = 0;
		results.maxWorkingTemp = 10;
		results.maxWorkingTempBender = 10;
		results.maxWorkingTempCrawler = 0;	
		
		testFactory((Factory)dict.get("b"), results);
	}

	/**
	 * Main entry point of the testing suite.
	 * 
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) throws Exception {
		
		try {
			Bender bender = new Bender(1);
			Crawler crawler = new Crawler(2);
			
			testRobot(bender);
			testRobot(crawler);
			
			testBender(bender);
			testCrawler(crawler);
			
			testDictionary();
			testRoles();
			
			testFactory();
			testFactoryDictionary();
	
			printSummary();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.printf(
				"%n%nUnexpected exception thrown after %d tests; testing aborted!%n",
				testCount);
		}
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
}
/* vim: set noet ts=4 sw=4: */
