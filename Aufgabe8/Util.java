public class Util {
	private static boolean verbose = false;
	public static void debug(String s) {
		if (verbose) {
			System.err.println(s);
		}
	}
}

/* vim: set noet ts=4 sw=4: */
