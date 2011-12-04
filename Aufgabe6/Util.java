public class Util {
	public static void checkNullArg(Object o) {
		if (o==null) {
			throw new IllegalArgumentException();
		}
	}

	public static void checkInstanceOf(Object o, String c) {
		checkNullArg(c);
		try {
			if (c.equals("") || !Class.forName(c).isInstance(o)) {
				throw new IllegalArgumentException();
			}
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException();
		}
	}
}
