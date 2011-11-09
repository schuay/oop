import java.util.HashMap;

public class Test {
	private static boolean tests = true;
	private static HashMap<Boolean, String> labels = new HashMap<Boolean, String>();
	private static int testCount = 0;
	private static int failedCount = 0;

	static {
		labels.put(true, "pass");
		labels.put(false, "FAIL");
	}

	/**
	 * Main entry point of the testing suite.
	 * @param args command line arguments
	 */
	public static void main(String[] args) throws Exception {
	}

	/**
	 * Prints a single test result.
	 * @param test Test description
	 * @param expected Expected test outcome
	 * @param got Actual test outcome
	 */
	private static void test(String test, Object expected, Object got) {
		boolean succeeded = expected.equals(got);
		if (!succeeded) {
			failedCount++;
		}
		System.out.printf("%04d: %s%n%s: expected: %s, got: %s%n%n", testCount++, test,
				labels.get(succeeded), abbreviate(expected.toString()),
				abbreviate(got.toString()));
		tests = tests && succeeded;
	}

	private static String abbreviate(String str) {
		final int cutofflen = 50;

		if (!str.contains("\n") && str.length() < cutofflen) {
			return str;
		}

		int cutoff = Math.min(cutofflen, str.indexOf('\n'));
		return str.substring(0, cutoff) + " [...]";
	}
}
/* vim: set noet ts=4 sw=4: */
