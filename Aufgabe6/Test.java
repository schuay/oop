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

	/**
	 * Main entry point of the testing suite.
	 * 
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) throws Exception {

		printSummary();
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
