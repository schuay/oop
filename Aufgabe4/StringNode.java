public class StringNode {
	/* a node consists of a value and
	 * left and right child nodes
	 * value is of type String */
	
	private StringNode left = null;

	private StringNode right = null;
	private final String value;
	private final int level;

	public StringNode(String value, int level) {
		if (value == null || value.equals("")) {
			throw new IllegalArgumentException();
		}

		this.value = value;
		this.level = level;
	}

	/* returns true if node has both a left and a right child */
	public boolean isFull() {
		return (hasLeft() && hasRight());
	}

	/* returns true if node has a left child */
	public boolean hasLeft() {
		return (left != null);
	}

	/* returns true if node has a right child */
	public boolean hasRight() {
		return (right != null);
	}

	/* returns a value < 0, == 0, >0 if value is, respectively,
	 * less than, equal to, or greater than anotherNode's value */
	public int compareTo(StringNode anotherNode) {
		return compareTo(anotherNode.value);
	}

	/* returns a value < 0, == 0, >0 if value is, respectively,
	 * less than, equal to, or greater than i */
	public int compareTo(String s) {
		return value.compareTo(s);
	}
	
	public StringNode getLeft() {
		return left;
	}
	
	public StringNode getRight() {
		return right;
	}
	
	public void setLeft(StringNode left) {
		this.left = left;
	}

	public void setRight(StringNode right) {
		this.right = right;
	}
	
	public String getValue() {
		return value;
	}

	public int getLevel() {
		return level;
	}

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
			s += StringNode.formatEmptyRow(getLevel() + 1);
		}
		if (hasRight()) {
			s += getRight().toStringTree();
		} else {
			s += StringNode.formatEmptyRow(getLevel() + 1);
		}

		return s;
	}

	protected static String formatEmptyRow(int level) {
		return StringNode.formatRow(level, "");
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

	/* returns a string representing the current node's value and level:
	 * value		// level 0
	 * - value		// level 1
	 *   - value	// level 2
	 */
	public String toString() {
		return StringNode.formatRow(getLevel(), getValue().toString());
	}
}
/* vim: set noet ts=4 sw=4: */
