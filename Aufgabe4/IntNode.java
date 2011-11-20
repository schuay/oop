public class IntNode extends Node {
	
	private IntNode left = null;
	private IntNode right = null;
	private final int value;
	private final int level;

	public IntNode(int value, int level) {
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

	public int compareTo(IntNode anotherNode) {
		return (value - anotherNode.value);
	}
	
	public IntNode getLeft() {
		return left;
	}
	
	public IntNode getRight() {
		return right;
	}
	
	public int getValue() {
		return value;
	}

	public int getLevel() {
		return level;
	}
}