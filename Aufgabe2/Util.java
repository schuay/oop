public class Util {
	public static boolean stringIsNullOrEmpty(String s) {
		return (isNull(s) || "".equals(s));
	}

	public static boolean isNull(Object o) {
		return (o == null);
	}

	public static String validateString(String s) throws IllegalArgumentException {
		if (stringIsNullOrEmpty(s)) {
			throw new IllegalArgumentException();
		}

		return s;
	}

	public static Object validateObject(Object o) throws IllegalArgumentException {
		if (isNull(o)) {
			throw new IllegalArgumentException();
		}

		return o;
	}
}
