public class IntNode extends Node {
	
	private IntNode left = null;
	private IntNode right = null;
	private final Integer value;
	private final int level;

	public IntNode(int value, int level) {
		this.value = value;
		this.level = level;
	}

	public boolean hasLeft() {
		return (left != null);
	}

	public boolean hasRight() {
		return (right != null);
	}

	public int compareTo(IntNode anotherNode) {
		return (value - anotherNode.value);
	}

	public int compareTo(int i) {
		return (value - i);
	}
	
	public IntNode getLeft() {
		return left;
	}
	
	public IntNode getRight() {
		return right;
	}
	
	public void setLeft(IntNode left) {
		this.left = left;
	}

	public void setRight(IntNode right) {
		this.right = right;
	}
	
	public Integer getValue() {
		return value;
	}

	public int getLevel() {
		return level;
	}

	public String toString() {
		return Node.formatRow(level, getValue().toString());
	}
}