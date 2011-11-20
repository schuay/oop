public abstract class Node {

	public abstract boolean hasLeft();
	public abstract boolean hasRight();
	public boolean isFull() {
		return (hasLeft() && hasRight());
	}

	public abstract Node getLeft();
	public abstract Node getRight();

	public abstract int getLevel();

	public String toStringTree() {
		String s = toString();
		if (hasLeft()) {
			s += getLeft().toStringTree();
		} else {
			s += Node.formatEmptyRow(getLevel() + 1);
		}
		if (hasRight()) {
			s += getRight().toStringTree();
		} else {
			s += Node.formatEmptyRow(getLevel() + 1);
		}

		return s;
	}

	protected static String formatEmptyRow(int level) {
		return Node.formatRow(level, "");
	}

	protected static String formatRow(int level, String value) {
		return String.format("%s%s%n", repeat("  ", "- ", level), value);
	}

	/* repeats s (n - 1) times with an appended terminator.
	 * passing n <= 0 results in an empty string. */
	protected static String repeat(String s, String terminator, int n) {
	    if (n <= 0) {
		return "";
	    } else if (n == 1) {
		return terminator;
	    }
	    return s + repeat(s, terminator, n - 1);
	}
}