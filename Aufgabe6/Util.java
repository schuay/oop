public class Util {
	public static void checkNullArg(Object o) {
		if (o==null) {
			throw new IllegalArgumentException();
		}
	}
}
