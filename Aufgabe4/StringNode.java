public class StringNode extends Node {
	
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
		return Node.formatRow(level, getValue());
	}
}