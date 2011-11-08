/**
 * This class contains some static utility functions, which are used to check
 * function parameters elsewhere.
 */
public class Util {
	public static boolean stringIsNullOrEmpty(String s) {
		return (s == null || "".equals(s));
	}

	public static String validateString(String s) throws IllegalArgumentException {
		if (stringIsNullOrEmpty(s)) {
			throw new IllegalArgumentException();
		}

		return s;
	}

	public static <T> T validateObject(T o) throws IllegalArgumentException {
		if (o == null) {
			throw new IllegalArgumentException();
		}

		return o;
	}
}
/* vim: set noet ts=4 sw=4: */
