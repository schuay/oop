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

	public static Object validateObject(Object o) throws IllegalArgumentException {
		if (o == null) {
			throw new IllegalArgumentException();
		}

		return o;
	}
}
