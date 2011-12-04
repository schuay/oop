public class Util {

	/* Throws an IllegalArgumentException if and only if o == null. */
	public static void checkNullArg(Object o) {
		if (o==null) {
			throw new IllegalArgumentException();
		}
	}

	/* Throws an IllegalArgumentException if and only if o == null or
	 * o is not an instance of the class specified in c or c doesn't
	 * specify a known class. */
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
