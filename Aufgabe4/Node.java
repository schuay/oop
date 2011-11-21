public abstract class Node {
	/* a node consists of a value and
	 * left and right child nodes */

	/* returns true if node has a left child */
	public abstract boolean hasLeft();

	/* returns true if node has a right child */
	public abstract boolean hasRight();

	/* returns true if node has both a left and a right child */
	public boolean isFull() {
		return (hasLeft() && hasRight());
	}

	public abstract Node getLeft();
	public abstract Node getRight();

	/* returns the level of the node within the tree */
	public abstract int getLevel();

	/* returns a string representation of the node and its subtrees:
	 * value			// this node's value
	 * - child 1		// value of the left child
	 *   - child 2		// value of the left child of the left child
	 *   - 				// empty leaf (right child of left child)
	 * -				// empty leaf (right child)
	 */
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
/* vim: set noet ts=4 sw=4: */
