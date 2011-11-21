public class StringNode extends Node {
	/* value is of type Integer */
	
	private StringNode left = null;

	private StringNode right = null;
	private final String value;
	private final int level;

	public StringNode(String value, int level) {
		this.value = value;
		this.level = level;
	}

	public boolean hasLeft() {
		return (left != null);
	}

	public boolean hasRight() {
		return (right != null);
	}

	/* returns a value < 0, == 0, >0 if value is, respectively,
	 * less than, equal to, or greater than anotherNode's value */
	public int compareTo(StringNode anotherNode) {
		return value.compareTo(anotherNode.value);
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

	public String toString() {
		return Node.formatRow(level, getValue());
	}
}
/* vim: set noet ts=4 sw=4: */
