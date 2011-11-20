public class StringNode implements Node {
	
	private StringNode left = null;

	private StringNode right = null;
	private final String value;
	private final int level;

	public StringNode(String value, int level) {
		this.value = value;
		this.level = level;
	}

	public boolean isFull() {
		return (hasLeft() && hasRight());
	}

	public boolean hasLeft() {
		return (left != null);
	}

	public boolean hasRight() {
		return (right != null);
	}

	public int compareTo(StringNode anotherNode) {
		return value.compareTo(anotherNode.value);
	}

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

	public String toString() {
		return String.format("%s%s%n",
				repeat("  ", "- ", level), getValue());
	}

	/* repeats s (n - 1) times with an appended terminator.
	 * passing n <= 0 results in an empty string. */
	private String repeat(String s, String terminator, int n) {
	    if (n <= 0) {
		return "";
	    } else if (n == 1) {
		return terminator;
	    }
	    return s + repeat(s, terminator, n - 1);
	}
}